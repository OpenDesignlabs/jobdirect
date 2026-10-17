package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.EducationLevel
import com.example.data.model.EligibilityStatus
import com.example.data.model.EligibilityVerdict
import com.example.data.model.RecruitmentBoard
import com.example.data.model.RecruitmentNotice
import com.example.ui.JobDirectUiState
import com.example.ui.components.BoardPill
import com.example.ui.components.CorrigendumBadge
import com.example.ui.components.DeadlineBadge
import com.example.ui.theme.AdminNavy
import com.example.ui.theme.NoticeAmber
import com.example.ui.theme.NoticeAmberContainer
import com.example.ui.theme.OnNoticeAmberContainer
import com.example.ui.theme.OnVerifiedEmeraldContainer
import com.example.ui.theme.SaffronPrimary
import com.example.ui.theme.VerifiedEmerald
import com.example.ui.theme.VerifiedEmeraldContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecruitmentFeedScreen(
    uiState: JobDirectUiState,
    recruitments: List<RecruitmentNotice>,
    savedIds: Set<String>,
    onSelectRecruitment: (String) -> Unit,
    onToggleSave: (String) -> Unit,
    onSearchChange: (String) -> Unit,
    onBoardChange: (RecruitmentBoard) -> Unit,
    onEducationChange: (EducationLevel) -> Unit,
    onToggleCorrigendaFilter: () -> Unit,
    onToggleLanguage: () -> Unit,
    evaluateEligibility: (RecruitmentNotice) -> EligibilityVerdict,
    modifier: Modifier = Modifier
) {
    val isGu = uiState.isGujarati

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Search & Filter header
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                // Main Bilingual State Toggle Banner
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFF1F5F9))
                        .padding(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Gujarati Summary Pill
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isGu) SaffronPrimary else Color.Transparent)
                            .clickable { if (!isGu) onToggleLanguage() }
                            .padding(vertical = 7.dp)
                            .testTag("feed_toggle_gujarati_btn"),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "ગુજરાતી સારાંશ",
                                color = if (isGu) Color.White else Color(0xFF475569),
                                fontSize = 12.sp,
                                fontWeight = if (isGu) FontWeight.Bold else FontWeight.Medium
                            )
                            if (isGu) {
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    }

                    // English Summary Pill
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (!isGu) AdminNavy else Color.Transparent)
                            .clickable { if (isGu) onToggleLanguage() }
                            .padding(vertical = 7.dp)
                            .testTag("feed_toggle_english_btn"),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "English Summary",
                                color = if (!isGu) Color.White else Color(0xFF475569),
                                fontSize = 12.sp,
                                fontWeight = if (!isGu) FontWeight.Bold else FontWeight.Medium
                            )
                            if (!isGu) {
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Search Input
                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = onSearchChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("recruitment_search_input"),
                    placeholder = {
                        Text(
                            text = if (isGu) "જાહેરખબર ક્રમાંક, પદ અથવા ખાતું શોધો..." else "Search advt no, post, or department...",
                            fontSize = 13.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    },
                    trailingIcon = {
                        if (uiState.searchQuery.isNotEmpty()) {
                            IconButton(onClick = { onSearchChange("") }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear search",
                                    tint = Color.Gray
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SaffronPrimary,
                        unfocusedBorderColor = Color(0xFFCBD5E1)
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Board Filter Chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RecruitmentBoard.values().forEach { board ->
                        val isSelected = uiState.selectedBoard == board
                        FilterChip(
                            selected = isSelected,
                            onClick = { onBoardChange(board) },
                            label = {
                                Text(
                                    text = if (board == RecruitmentBoard.ALL) {
                                        if (isGu) "તમામ બોર્ડ" else "All Boards"
                                    } else {
                                        board.code
                                    },
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = AdminNavy,
                                selectedLabelColor = Color.White
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Education & Corrigendum Toggle Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Corrigenda Only Filter Chip
                    FilterChip(
                        selected = uiState.onlyWithCorrigendum,
                        onClick = onToggleCorrigendaFilter,
                        label = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.NotificationsActive,
                                    contentDescription = null,
                                    tint = if (uiState.onlyWithCorrigendum) Color.White else NoticeAmber,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isGu) "સુધારા નોટિસ વાળી (Corrigendum)" else "Corrigenda Only",
                                    fontSize = 12.sp,
                                    fontWeight = if (uiState.onlyWithCorrigendum) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = NoticeAmber,
                            selectedLabelColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )

                    // Education Filter Chips
                    EducationLevel.values().filter { it != EducationLevel.ANY }.forEach { edu ->
                        val isSelected = uiState.selectedEducation == edu
                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                onEducationChange(if (isSelected) EducationLevel.ANY else edu)
                            },
                            label = {
                                Text(
                                    text = if (isGu) edu.labelGu else edu.labelEn,
                                    fontSize = 12.sp
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SaffronPrimary,
                                selectedLabelColor = Color.White
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                }
            }
        }

        // Summary Statistics Banner
        Surface(
            color = Color(0xFFF1F5F9),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.VerifiedUser,
                        contentDescription = null,
                        tint = VerifiedEmerald,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isGu) "ચકાસાયેલી ભરતીઓ: ${recruitments.size}" else "Verified Openings: ${recruitments.size}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF334155)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Translate,
                        contentDescription = null,
                        tint = SaffronPrimary,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isGu) "ગુજરાતી-પ્રથમ સારાંશ" else "English Summaries",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF0F172A)
                    )
                }
            }
        }

        // List of Recruitments
        if (recruitments.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = if (isGu) "આ ફિલ્ટર માટે કોઈ ભરતી મળી નથી" else "No recruitments match your filters",
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (isGu) "કૃપા કરીને અન્ય બોર્ડ અથવા લાયકાત પસંદ કરો" else "Try clearing search or changing board filter",
                        color = Color.LightGray,
                        fontSize = 13.sp
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(recruitments, key = { it.id }) { notice ->
                    val isSaved = savedIds.contains(notice.id)
                    val verdict = evaluateEligibility(notice)

                    RecruitmentCard(
                        notice = notice,
                        isGujarati = isGu,
                        isSaved = isSaved,
                        verdict = verdict,
                        onCardClick = { onSelectRecruitment(notice.id) },
                        onToggleSave = { onToggleSave(notice.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun RecruitmentCard(
    notice: RecruitmentNotice,
    isGujarati: Boolean,
    isSaved: Boolean,
    verdict: EligibilityVerdict,
    onCardClick: () -> Unit,
    onToggleSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Allows inline language toggling for this specific card
    var localLangOverride by remember(isGujarati) { mutableStateOf<Boolean?>(null) }
    val cardIsGu = localLangOverride ?: isGujarati

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
            .testTag("recruitment_card_${notice.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row: Advt No, Board Pill, Corrigendum Badge, Language Preview Pill, Bookmark Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    BoardPill(board = notice.board, isGujarati = cardIsGu)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = notice.advtNo,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = AdminNavy
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (notice.hasCorrigendum) {
                        CorrigendumBadge(
                            isGujarati = cardIsGu,
                            count = notice.corrigendumCount
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                    }

                    // Card inline language preview toggle
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { localLangOverride = !cardIsGu }
                            .testTag("card_lang_toggle_${notice.id}"),
                        color = Color(0xFFF1F5F9),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Translate,
                                contentDescription = "Toggle card language",
                                tint = if (cardIsGu) SaffronPrimary else AdminNavy,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = if (cardIsGu) "GU" else "EN",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (cardIsGu) SaffronPrimary else AdminNavy
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    IconButton(
                        onClick = onToggleSave,
                        modifier = Modifier.size(36.dp).testTag("save_btn_${notice.id}")
                    ) {
                        Icon(
                            imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = if (isSaved) "Remove from tracker" else "Add to tracker",
                            tint = if (isSaved) VerifiedEmerald else Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = if (cardIsGu) notice.titleGu else notice.titleEn,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Department
            Text(
                text = if (cardIsGu) notice.departmentGu else notice.departmentEn,
                fontSize = 12.sp,
                color = Color(0xFF64748B),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // BILINGUAL NOTIFICATION SUMMARY BOX
            Surface(
                color = if (cardIsGu) Color(0xFFFFF7ED) else Color(0xFFF0F9FF),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (cardIsGu) Color(0xFFFFEDD5) else Color(0xFFE0F2FE)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (cardIsGu) "📢 સત્તાવાર સારાંશ અને નોંધ:" else "📢 Notification Summary & Highlights:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (cardIsGu) Color(0xFF9A3412) else Color(0xFF0369A1)
                        )
                        Text(
                            text = if (cardIsGu) "ચકાસાયેલ" else "Verified Source",
                            fontSize = 10.sp,
                            color = Color(0xFF059669),
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = if (cardIsGu) notice.verificationNotesGu else notice.verificationNotesEn,
                        fontSize = 12.sp,
                        color = Color(0xFF1E293B),
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${if (cardIsGu) "શૈક્ષણિક લાયકાત:" else "Education Requirement:"} ${if (cardIsGu) notice.educationDetailsGu else notice.educationDetailsEn}",
                        fontSize = 11.sp,
                        color = Color(0xFF475569)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Key Metrics Pill Row (Vacancies, Cadre, Pay)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    color = Color(0xFFF8FAFC),
                    shape = RoundedCornerShape(6.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Text(
                        text = if (cardIsGu) "કુલ જગ્યા: ${notice.totalVacancies}" else "Vacancies: ${notice.totalVacancies}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SaffronPrimary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Surface(
                    color = Color(0xFFF8FAFC),
                    shape = RoundedCornerShape(6.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Text(
                        text = if (cardIsGu) notice.postCadreGu else notice.postCadreEn,
                        fontSize = 11.sp,
                        color = AdminNavy,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Surface(
                    color = Color(0xFFF8FAFC),
                    shape = RoundedCornerShape(6.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Text(
                        text = if (cardIsGu) notice.educationLevel.labelGu else notice.educationLevel.labelEn,
                        fontSize = 11.sp,
                        color = Color(0xFF475569),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Eligibility Verdict Quick Banner
            val (verdictBg, verdictFg) = when (verdict.status) {
                EligibilityStatus.ELIGIBLE -> VerifiedEmeraldContainer to OnVerifiedEmeraldContainer
                EligibilityStatus.AGE_OVER_LIMIT, EligibilityStatus.AGE_UNDER_LIMIT -> NoticeAmberContainer to OnNoticeAmberContainer
                EligibilityStatus.EDUCATION_MISMATCH -> Color(0xFFFEE2E2) to Color(0xFF991B1B)
                else -> Color(0xFFF1F5F9) to Color(0xFF334155)
            }

            Surface(
                color = verdictBg,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (verdict.status == EligibilityStatus.ELIGIBLE) Icons.Default.CheckCircle else Icons.Default.NotificationsActive,
                        contentDescription = null,
                        tint = verdictFg,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (cardIsGu) verdict.statusTitleGu else verdict.statusTitleEn,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = verdictFg
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = if (cardIsGu) "ઉંમર: ${verdict.calculatedAgeYears} વર્ષ" else "Age: ${verdict.calculatedAgeYears}y",
                        fontSize = 11.sp,
                        color = verdictFg
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Footer Row: Deadline Badge & Action Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DeadlineBadge(
                    endDate = notice.applicationEndDate,
                    isExtended = notice.isDeadlineExtended,
                    isGujarati = cardIsGu
                )

                Button(
                    onClick = onCardClick,
                    colors = ButtonDefaults.buttonColors(containerColor = AdminNavy),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (cardIsGu) "વિગતો અને ચેકલિસ્ટ" else "Details & Checklist",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
