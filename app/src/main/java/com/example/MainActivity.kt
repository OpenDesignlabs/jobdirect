package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.AppScreen
import com.example.ui.JobDirectViewModel
import com.example.ui.components.JobDirectBottomBar
import com.example.ui.components.JobDirectTopBar
import com.example.ui.screens.CorrigendaScreen
import com.example.ui.screens.EligibilityScreen
import com.example.ui.screens.MyApplicationsScreen
import com.example.ui.screens.RecruitmentDetailScreen
import com.example.ui.screens.RecruitmentFeedScreen
import com.example.ui.theme.JobDirectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JobDirectTheme {
                JobDirectApp()
            }
        }
    }
}

@Composable
fun JobDirectApp(viewModel: JobDirectViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val savedApplications by viewModel.savedApplications.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearSnackbarMessage()
        }
    }

    val savedIds = remember(savedApplications) {
        savedApplications.map { it.recruitmentId }.toSet()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
        topBar = {
            if (uiState.currentScreen != AppScreen.DETAIL_VIEW) {
                JobDirectTopBar(
                    isGujarati = uiState.isGujarati,
                    onToggleLanguage = { viewModel.toggleLanguage() }
                )
            }
        },
        bottomBar = {
            if (uiState.currentScreen != AppScreen.DETAIL_VIEW) {
                JobDirectBottomBar(
                    currentScreen = uiState.currentScreen,
                    isGujarati = uiState.isGujarati,
                    corrigendaCount = viewModel.allCorrigenda.size,
                    savedCount = savedApplications.size,
                    onNavigate = { screen -> viewModel.navigateTo(screen) },
                    modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState.currentScreen) {
                AppScreen.RECRUITMENTS -> {
                    RecruitmentFeedScreen(
                        uiState = uiState,
                        recruitments = viewModel.getFilteredRecruitments(),
                        savedIds = savedIds,
                        onSelectRecruitment = { id -> viewModel.openRecruitmentDetail(id) },
                        onToggleSave = { id -> viewModel.toggleSave(id) },
                        onSearchChange = { q -> viewModel.setSearchQuery(q) },
                        onBoardChange = { board -> viewModel.setSelectedBoard(board) },
                        onEducationChange = { edu -> viewModel.setSelectedEducation(edu) },
                        onToggleCorrigendaFilter = { viewModel.toggleOnlyWithCorrigendum() },
                        onToggleLanguage = { viewModel.toggleLanguage() },
                        evaluateEligibility = { notice -> viewModel.evaluateSingleEligibility(notice) }
                    )
                }

                AppScreen.DETAIL_VIEW -> {
                    val notice = viewModel.allRecruitments.find { it.id == uiState.selectedRecruitmentId }
                        ?: viewModel.allRecruitments.first()
                    val corrigenda = viewModel.getCorrigendaForRecruitment(notice.id)
                    val savedEntity = viewModel.getSavedApplication(notice.id)
                    val verdict = viewModel.evaluateSingleEligibility(notice)

                    RecruitmentDetailScreen(
                        notice = notice,
                        corrigenda = corrigenda,
                        isGujarati = uiState.isGujarati,
                        isSaved = savedIds.contains(notice.id),
                        savedEntity = savedEntity,
                        standardChecklist = viewModel.standardChecklist,
                        verdict = verdict,
                        onBack = { viewModel.navigateTo(AppScreen.RECRUITMENTS) },
                        onToggleSave = { viewModel.toggleSave(notice.id) },
                        onUpdateStage = { stage -> viewModel.updateApplicationStage(notice.id, stage) },
                        onToggleDocCheck = { docId -> viewModel.toggleDocumentCheck(notice.id, docId) },
                        onSaveOjasDetails = { confirmNo, rollNo, notes ->
                            viewModel.saveOjasDetails(notice.id, confirmNo, rollNo, notes)
                        },
                        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
                    )
                }

                AppScreen.CORRIGENDA -> {
                    CorrigendaScreen(
                        corrigenda = viewModel.allCorrigenda,
                        isGujarati = uiState.isGujarati,
                        onSelectRecruitment = { id -> viewModel.openRecruitmentDetail(id) }
                    )
                }

                AppScreen.ELIGIBILITY_CALCULATOR -> {
                    EligibilityScreen(
                        profile = uiState.candidateProfile,
                        evaluatedRecruitments = viewModel.getEligibleRecruitmentsList(),
                        isGujarati = uiState.isGujarati,
                        onUpdateProfile = { newProfile -> viewModel.updateCandidateProfile(newProfile) },
                        onSelectRecruitment = { id -> viewModel.openRecruitmentDetail(id) }
                    )
                }

                AppScreen.MY_APPLICATIONS -> {
                    MyApplicationsScreen(
                        savedApplications = savedApplications,
                        allRecruitments = viewModel.allRecruitments,
                        isGujarati = uiState.isGujarati,
                        onSelectRecruitment = { id -> viewModel.openRecruitmentDetail(id) },
                        onRemoveSaved = { id -> viewModel.toggleSave(id) },
                        onExploreClick = { viewModel.navigateTo(AppScreen.RECRUITMENTS) }
                    )
                }
            }
        }
    }
}
