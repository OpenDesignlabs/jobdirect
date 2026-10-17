package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Launch
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.SavedApplicationEntity
import com.example.data.model.CorrigendumNotice
import com.example.data.model.DocumentChecklistItem
import com.example.data.model.EligibilityStatus
import com.example.data.model.EligibilityVerdict
import com.example.data.model.RecruitmentNotice
import com.example.ui.components.BoardPill
import com.example.ui.components.CorrigendumBadge
import com.example.ui.components.DeadlineBadge
import com.example.ui.theme.AdminNavy
import com.example.ui.theme.NoticeAmber
import com.example.ui.theme.NoticeAmberContainer
import com.example.ui.theme.OnNoticeAmberContainer
import com.example.ui.theme.OnSaffronContainer
import com.example.ui.theme.OnVerifiedEmeraldContainer
import com.example.ui.theme.SaffronContainer
import com.example.ui.theme.SaffronPrimary
import com.example.ui.theme.UrgentRed
import com.example.ui.theme.VerifiedEmerald
import com.example.ui.theme.VerifiedEmeraldContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecruitmentDetailScreen(
    notice: RecruitmentNotice,
    corrigenda: List<CorrigendumNotice>,
    isGujarati: Boolean,
    isSaved: Boolean,
    savedEntity: SavedApplicationEntity?,
    standardChecklist: List<DocumentChecklistItem>,
    verdict: EligibilityVerdict,
    onBack: () -> Unit,
    onToggleSave: () -> Unit,
    onUpdateStage: (String) -> Unit,
    onToggleDocCheck: (String) -> Unit,
    onSaveOjasDetails: (confirmNo: String, rollNo: String, notes: String) -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    val context = LocalContext.current
    val isGu = isGujarati

    // Completed document set
    val completedDocs = remember(savedEntity?.completedDocumentIdsCommaSeparated) {
        savedEntity?.completedDocumentIdsCommaSeparated
            ?.split(",")
            ?.filter { it.isNotBlank() }
            ?.toSet() ?: emptySet()
    }

    // OJAS Tracking fields
    var confirmNoInput by remember(savedEntity?.ojasConfirmationNumber) {
        mutableStateOf(savedEntity?.ojasConfirmationNumber ?: "")
    }
    var rollNoInput by remember(savedEntity?.ojasApplicationNumber) {
        mutableStateOf(savedEntity?.ojasApplicationNumber ?: "")
    }
    var notesInput by remember(savedEntity?.candidateNotes) {
        mutableStateOf(savedEntity?.candidateNotes ?: "")
    }

    val currentStage = savedEntity?.applicationStage ?: "SAVED"

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Detail Header
        Surface(
            color = AdminNavy,
            shadowElevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack, modifier = Modifier.testTag("detail_back_btn")) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = notice.advtNo,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF90CDF4)
                    )
                    Text(
                        text = if (isGu) notice.board.nameGu else notice.board.nameEn,
                        fontSize = 11.sp,
                        color = Color.White.copy(alpha = 0.85f),
                        maxLines = 1
                    )
                }

                IconButton(
                    onClick = onToggleSave,
                    modifier = Modifier.testTag("detail_toggle_save_btn")
                ) {
                    Icon(
                        imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = "Save",
                        tint = if (isSaved) VerifiedEmerald else Color.White
                    )
                }
            }
        }

        // Scrollable Body
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Main Card: Title, Badges, Department
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BoardPill(board = notice.board, isGujarati = isGu)
                        DeadlineBadge(
                            endDate = notice.applicationEndDate,
                            isExtended = notice.isDeadlineExtended,
                            isGujarati = isGu
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = if (isGu) notice.titleGu else notice.titleEn,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = if (isGu) notice.departmentGu else notice.departmentEn,
                        fontSize = 13.sp,
                        color = Color(0xFF64748B)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Key stats grid (Vacancies, Cadre, Pay)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = if (isGu) "કુલ જગ્યાઓ" else "Total Vacancies",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = "${notice.totalVacancies}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = SaffronPrimary
                            )
                        }

                        Column {
                            Text(
                                text = if (isGu) "સંવર્ગ" else "Cadre",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = if (isGu) notice.postCadreGu else notice.postCadreEn,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = AdminNavy
                            )
                        }

                        Column {
                            Text(
                                text = if (isGu) "વય કટ-ઓફ" else "Age Cutoff",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = notice.cutoffDateForAge,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF334155)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Vacancy breakdown text
                    Surface(
                        color = Color(0xFFF8FAFC),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = if (isGu) "જગ્યાઓની વિગતવાર વહેંચણી:" else "Vacancies Breakdown:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF475569)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = if (isGu) notice.vacanciesBreakdownGu else notice.vacanciesBreakdownEn,
                                fontSize = 12.sp,
                                color = Color(0xFF1E293B)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Pay scale
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Payments,
                            contentDescription = null,
                            tint = Color(0xFF0284C7),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isGu) "પગાર ધોરણ: ${notice.payScaleGu}" else "Pay Scale: ${notice.payScaleEn}",
                            fontSize = 12.sp,
                            color = Color(0xFF334155),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Corrigenda & Change Alert Card (Highlighting differences!)
            if (notice.hasCorrigendum && corrigenda.isNotEmpty()) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = NoticeAmberContainer.copy(alpha = 0.6f)),
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, NoticeAmber)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.NotificationsActive,
                                contentDescription = null,
                                tint = NoticeAmber,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isGu) "સત્તાવાર સુધારા પત્રક ચેતવણી (Corrigendum Alert)" else "Official Corrigendum Notice Alert",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = OnNoticeAmberContainer
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        corrigenda.forEachIndexed { index, corr ->
                            if (index > 0) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Divider(color = NoticeAmber.copy(alpha = 0.4f))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            Text(
                                text = "${corr.corrigendumNo} (${corr.issueDate})",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = AdminNavy
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = if (isGu) corr.headlineGu else corr.headlineEn,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = OnNoticeAmberContainer
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            // Comparison Before / After
                            Surface(
                                color = Color.White.copy(alpha = 0.85f),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Row {
                                        Text(
                                            text = if (isGu) "અગાઉ (મૂળ): " else "Original: ",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFDC2626)
                                        )
                                        Text(
                                            text = if (isGu) corr.originalValueTextGu else corr.originalValueTextEn,
                                            fontSize = 11.sp,
                                            color = Color(0xFF334155)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row {
                                        Text(
                                            text = if (isGu) "સુધારેલ (નવું): " else "Revised: ",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF15803D)
                                        )
                                        Text(
                                            text = if (isGu) corr.revisedValueTextGu else corr.revisedValueTextEn,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF15803D)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Personalized Candidate Eligibility Status Box
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isGu) "તમારી પાત્રતા ચકાસણી" else "Your Eligibility Verdict",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        val statusPillBg = when (verdict.status) {
                            EligibilityStatus.ELIGIBLE -> VerifiedEmeraldContainer
                            EligibilityStatus.AGE_OVER_LIMIT, EligibilityStatus.AGE_UNDER_LIMIT -> NoticeAmberContainer
                            else -> Color(0xFFFEE2E2)
                        }
                        val statusPillFg = when (verdict.status) {
                            EligibilityStatus.ELIGIBLE -> OnVerifiedEmeraldContainer
                            EligibilityStatus.AGE_OVER_LIMIT, EligibilityStatus.AGE_UNDER_LIMIT -> OnNoticeAmberContainer
                            else -> Color(0xFF991B1B)
                        }

                        Surface(
                            color = statusPillBg,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = if (isGu) verdict.statusTitleGu else verdict.statusTitleEn,
                                color = statusPillFg,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isGu) verdict.explanationGu else verdict.explanationEn,
                        fontSize = 13.sp,
                        color = Color(0xFF334155),
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Age & Relaxations breakdown
                    Surface(
                        color = Color(0xFFF1F5F9),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = if (isGu) "કટ-ઓફ તારીખે ઉંમર:" else "Age on Cutoff:",
                                    fontSize = 12.sp,
                                    color = Color(0xFF64748B)
                                )
                                Text(
                                    text = "${verdict.calculatedAgeYears}y ${verdict.calculatedAgeMonths}m ${verdict.calculatedAgeDays}d",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0F172A)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = if (isGu) "માન્ય મહત્તમ વય:" else "Permissible Max Age:",
                                    fontSize = 12.sp,
                                    color = Color(0xFF64748B)
                                )
                                Text(
                                    text = "${verdict.permissibleMaxAge} Years (${verdict.baseMaxAge} + ${verdict.totalRelaxationYears} relax)",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SaffronPrimary
                                )
                            }

                            if (verdict.appliedRelaxationsEn.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = if (isGu) "લાગુ પડેલ સરકારી છૂટછાટો:" else "Applied Statutory Relaxations:",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF475569)
                                )
                                val relaxations = if (isGu) verdict.appliedRelaxationsGu else verdict.appliedRelaxationsEn
                                relaxations.forEach { rule ->
                                    Text(
                                        text = "• $rule",
                                        fontSize = 11.sp,
                                        color = Color(0xFF334155)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Application-Readiness Document Checklist
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = if (isGu) "અરજી-તૈયારી ચેકલિસ્ટ (OJAS)" else "Application-Readiness Checklist",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (isGu) "ફોર્મ ભરતા પહેલા જરૂરી પુરાવા અને સાઇઝ" else "Prepare docs & specs before applying",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                        }

                        val readyCount = standardChecklist.count { completedDocs.contains(it.id) }
                        Text(
                            text = "$readyCount / ${standardChecklist.size}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = VerifiedEmerald
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    val progress = if (standardChecklist.isNotEmpty()) {
                        completedDocs.size.toFloat() / standardChecklist.size.toFloat()
                    } else 0f

                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = VerifiedEmerald,
                        trackColor = Color(0xFFE2E8F0)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    standardChecklist.forEach { item ->
                        val isChecked = completedDocs.contains(item.id)

                        Surface(
                            color = if (isChecked) Color(0xFFF0FDF4) else Color(0xFFF8FAFC),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isChecked) VerifiedEmerald.copy(alpha = 0.5f) else Color(0xFFE2E8F0)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { onToggleDocCheck(item.id) }
                                .testTag("doc_check_${item.id}")
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = { onToggleDocCheck(item.id) },
                                    colors = CheckboxDefaults.colors(checkedColor = VerifiedEmerald)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = if (isGu) item.nameGu else item.nameEn,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isChecked) Color(0xFF166534) else MaterialTheme.colorScheme.onSurface
                                        )
                                        if (item.isMandatoryAtOjasApply) {
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Surface(
                                                color = Color(0xFFFEE2E2),
                                                shape = RoundedCornerShape(4.dp)
                                            ) {
                                                Text(
                                                    text = if (isGu) "ફરજિયાત" else "Required",
                                                    color = Color(0xFFDC2626),
                                                    fontSize = 9.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = if (isGu) item.specsGu else item.specsEn,
                                        fontSize = 11.sp,
                                        color = Color(0xFF475569)
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = if (isGu) "💡 ${item.ojasInstructionGu}" else "💡 ${item.ojasInstructionEn}",
                                        fontSize = 10.sp,
                                        color = Color(0xFF0369A1)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // OJAS Confirmation Tracker & Stage
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (isGu) "અરજી ટ્રેકિંગ અને કન્ફર્મેશન નંબર" else "OJAS Confirmation Tracker",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (isGu) "ફોર્મ સબમિટ કર્યા પછી મળેલ નંબર અહીં નોંધો" else "Save your OJAS confirmation number for hall tickets",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Stage Selector Chips
                    Text(
                        text = if (isGu) "અરજી સ્થિતિ (Status):" else "Application Status:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF334155)
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    val stages = listOf(
                        "SAVED" to (if (isGu) "રસ ધરાવો છો" else "Saved"),
                        "DOCS_READY" to (if (isGu) "ડોક્યુમેન્ટ તૈયાર" else "Docs Ready"),
                        "APPLIED_OJAS" to (if (isGu) "OJAS પર અરજી થઈ ગઈ" else "Applied"),
                        "ADMIT_CARD" to (if (isGu) "કોલ લેટર મળ્યો" else "Admit Card")
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        stages.forEach { (code, label) ->
                            val isSel = currentStage == code
                            FilterChip(
                                selected = isSel,
                                onClick = { onUpdateStage(code) },
                                label = { Text(text = label, fontSize = 10.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = VerifiedEmerald,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = confirmNoInput,
                        onValueChange = { confirmNoInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("ojas_confirm_input"),
                        label = {
                            Text(
                                text = if (isGu) "OJAS કન્ફર્મેશન નંબર (8 અંક)" else "OJAS Confirmation No",
                                fontSize = 12.sp
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = rollNoInput,
                        onValueChange = { rollNoInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("ojas_roll_input"),
                        label = {
                            Text(
                                text = if (isGu) "અરજી ક્રમાંક / રોલ નંબર" else "Application / Roll No",
                                fontSize = 12.sp
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = notesInput,
                        onValueChange = { notesInput = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(
                                text = if (isGu) "નોંધ (પરીક્ષા સેન્ટર, તૈયારી નોંધ)" else "Notes (Exam center, prep status)",
                                fontSize = 12.sp
                            )
                        },
                        maxLines = 3,
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            onSaveOjasDetails(confirmNoInput, rollNoInput, notesInput)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = AdminNavy),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("save_ojas_details_btn")
                    ) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isGu) "ટ્રેકર વિગતો સાચવો" else "Save Tracker Details",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Direct Official Links Action Buttons
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(notice.applyPortalUrl))
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("open_official_portal_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Launch,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isGu) "OJAS સત્તાવાર પોર્ટલ પર અરજી કરો" else "Apply on Official Portal (OJAS)",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                OutlinedButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(notice.officialPdfUrl))
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("view_official_pdf_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = null,
                        tint = AdminNavy
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isGu) "મૂળ સત્તાવાર જાહેરાત PDF જુઓ" else "View Original Notification PDF",
                        fontSize = 14.sp,
                        color = AdminNavy,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
