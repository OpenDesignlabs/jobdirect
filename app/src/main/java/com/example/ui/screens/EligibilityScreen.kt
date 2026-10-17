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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CandidateCategory
import com.example.data.model.CandidateProfile
import com.example.data.model.EducationLevel
import com.example.data.model.EligibilityStatus
import com.example.data.model.EligibilityVerdict
import com.example.data.model.Gender
import com.example.data.model.RecruitmentNotice
import com.example.ui.components.BoardPill
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
fun EligibilityScreen(
    profile: CandidateProfile,
    evaluatedRecruitments: List<Pair<RecruitmentNotice, EligibilityVerdict>>,
    isGujarati: Boolean,
    onUpdateProfile: (CandidateProfile) -> Unit,
    onSelectRecruitment: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isGu = isGujarati
    var isEditingProfile by remember { mutableStateOf(false) }

    val eligibleCount = evaluatedRecruitments.count { it.second.status == EligibilityStatus.ELIGIBLE }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Banner with Profile Summary
        Surface(
            color = AdminNavy,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isGu) "ગુજરાત ભરતી પાત્રતા કેલ્ક્યુલેટર" else "Gujarat Eligibility Calculator",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = if (isGu) "સત્તાવાર નિયમો મુજબ વયમર્યાદા છૂટછાટ અને પરિણામ" else "Deterministic Civil Service Age Relaxations",
                            fontSize = 11.sp,
                            color = Color(0xFF90CDF4)
                        )
                    }

                    Button(
                        onClick = { isEditingProfile = !isEditingProfile },
                        colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("toggle_profile_edit_btn")
                    ) {
                        Text(
                            text = if (isEditingProfile) {
                                if (isGu) "પૂર્ણ કરો" else "Done"
                            } else {
                                if (isGu) "પ્રોફાઇલ બદલો" else "Edit Profile"
                            },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Profile Highlights Pill
                Surface(
                    color = Color.White.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${if (isGu) "જન્મ:" else "DOB:"} ${profile.birthDay}/${profile.birthMonth}/${profile.birthYear}",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                        Text(
                            text = if (isGu) profile.category.labelGu else profile.category.code,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFDBCF)
                        )
                        Text(
                            text = if (isGu) profile.gender.labelGu else profile.gender.labelEn,
                            fontSize = 12.sp,
                            color = Color.White
                        )
                        Text(
                            text = if (isGu) profile.educationLevel.labelGu else profile.educationLevel.labelEn,
                            fontSize = 12.sp,
                            color = Color(0xFF90CDF4)
                        )
                    }
                }
            }
        }

        // Conditional Profile Edit Drawer/Card
        if (isEditingProfile) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = if (isGu) "તમારી ઉમેદવારી વિગત દાખલ કરો:" else "Enter Candidate Profile:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AdminNavy
                    )

                    // Date of Birth Inputs (Day, Month, Year)
                    Text(
                        text = if (isGu) "જન્મ તારીખ (Day / Month / Year):" else "Date of Birth (Day / Month / Year):",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF334155)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = profile.birthDay.toString(),
                            onValueChange = {
                                val d = it.toIntOrNull() ?: profile.birthDay
                                if (d in 1..31) onUpdateProfile(profile.copy(birthDay = d))
                            },
                            label = { Text("Day", fontSize = 11.sp) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp)
                        )
                        OutlinedTextField(
                            value = profile.birthMonth.toString(),
                            onValueChange = {
                                val m = it.toIntOrNull() ?: profile.birthMonth
                                if (m in 1..12) onUpdateProfile(profile.copy(birthMonth = m))
                            },
                            label = { Text("Month", fontSize = 11.sp) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp)
                        )
                        OutlinedTextField(
                            value = profile.birthYear.toString(),
                            onValueChange = {
                                val y = it.toIntOrNull() ?: profile.birthYear
                                if (y in 1960..2020) onUpdateProfile(profile.copy(birthYear = y))
                            },
                            label = { Text("Year", fontSize = 11.sp) },
                            modifier = Modifier.weight(1.5f),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp)
                        )
                    }

                    // Category Selection Chips
                    Text(
                        text = if (isGu) "અનામત કેટેગરી:" else "Category:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF334155)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        CandidateCategory.values().forEach { cat ->
                            val isSel = profile.category == cat
                            FilterChip(
                                selected = isSel,
                                onClick = { onUpdateProfile(profile.copy(category = cat)) },
                                label = { Text(cat.code, fontSize = 12.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = SaffronPrimary,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }

                    // Gender Selection
                    Text(
                        text = if (isGu) "જાતિ (Gender):" else "Gender:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF334155)
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Gender.values().forEach { g ->
                            val isSel = profile.gender == g
                            FilterChip(
                                selected = isSel,
                                onClick = { onUpdateProfile(profile.copy(gender = g)) },
                                label = {
                                    Text(
                                        text = if (isGu) g.labelGu else g.labelEn,
                                        fontSize = 12.sp
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = AdminNavy,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }

                    // Education Level
                    Text(
                        text = if (isGu) "ઉચ્ચતમ શૈક્ષણિક લાયકાત:" else "Highest Qualification:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF334155)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        EducationLevel.values().filter { it != EducationLevel.ANY }.forEach { edu ->
                            val isSel = profile.educationLevel == edu
                            FilterChip(
                                selected = isSel,
                                onClick = { onUpdateProfile(profile.copy(educationLevel = edu)) },
                                label = {
                                    Text(
                                        text = if (isGu) edu.labelGu else edu.labelEn,
                                        fontSize = 11.sp
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = SaffronPrimary,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }

                    // Toggles (Gujarat Domicile, PwD, Ex-Serviceman)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isGu) "ગુજરાતના વતની (Gujarat Domicile)" else "Gujarat Domicile",
                            fontSize = 13.sp
                        )
                        Switch(
                            checked = profile.isGujaratDomicile,
                            onCheckedChange = { onUpdateProfile(profile.copy(isGujaratDomicile = it)) },
                            colors = SwitchDefaults.colors(checkedThumbColor = SaffronPrimary)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isGu) "દિવ્યાંગ ઉમેદવાર (PwD +10y)" else "Differently Abled (PwD +10y)",
                            fontSize = 13.sp
                        )
                        Switch(
                            checked = profile.isDifferentlyAbled,
                            onCheckedChange = { onUpdateProfile(profile.copy(isDifferentlyAbled = it)) },
                            colors = SwitchDefaults.colors(checkedThumbColor = SaffronPrimary)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isGu) "માજી સૈનિક (Ex-Serviceman)" else "Ex-Serviceman",
                            fontSize = 13.sp
                        )
                        Switch(
                            checked = profile.isExServiceman,
                            onCheckedChange = { onUpdateProfile(profile.copy(isExServiceman = it, exServiceYears = if (it) 5 else 0)) },
                            colors = SwitchDefaults.colors(checkedThumbColor = SaffronPrimary)
                        )
                    }
                }
            }
        }

        // Qualification Result Summary Banner
        Surface(
            color = Color(0xFFF1F5F9),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = VerifiedEmerald,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isGu) "પાત્ર ભરતીઓ: $eligibleCount / ${evaluatedRecruitments.size}" else "You qualify for: $eligibleCount / ${evaluatedRecruitments.size}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = AdminNavy
                    )
                }

                Text(
                    text = if (isGu) "સરકારી વય છૂટછાટ સહિત" else "Govt relaxations included",
                    fontSize = 11.sp,
                    color = Color(0xFF64748B)
                )
            }
        }

        // List of Evaluated Recruitments
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(evaluatedRecruitments, key = { it.first.id }) { (notice, verdict) ->
                EligibilityResultCard(
                    notice = notice,
                    verdict = verdict,
                    isGujarati = isGu,
                    onClick = { onSelectRecruitment(notice.id) }
                )
            }
        }
    }
}

@Composable
fun EligibilityResultCard(
    notice: RecruitmentNotice,
    verdict: EligibilityVerdict,
    isGujarati: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val (bg, fg) = when (verdict.status) {
        EligibilityStatus.ELIGIBLE -> VerifiedEmeraldContainer to OnVerifiedEmeraldContainer
        EligibilityStatus.AGE_OVER_LIMIT, EligibilityStatus.AGE_UNDER_LIMIT -> NoticeAmberContainer to OnNoticeAmberContainer
        else -> Color(0xFFFEE2E2) to Color(0xFF991B1B)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("eligibility_result_${notice.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Row: Advt No, Board, Verdict Pill
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BoardPill(board = notice.board, isGujarati = isGujarati)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = notice.advtNo,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = AdminNavy
                    )
                }

                Surface(
                    color = bg,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = if (isGujarati) verdict.statusTitleGu else verdict.statusTitleEn,
                        color = fg,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = if (isGujarati) notice.titleGu else notice.titleEn,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 19.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Explanation
            Text(
                text = if (isGujarati) verdict.explanationGu else verdict.explanationEn,
                fontSize = 12.sp,
                color = Color(0xFF334155),
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Expandable Relaxation Formula
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (isGujarati) {
                        "વય ગણતરી: ${verdict.calculatedAgeYears} વર્ષ (મહત્તમ માન્ય: ${verdict.permissibleMaxAge} વર્ષ)"
                    } else {
                        "Age: ${verdict.calculatedAgeYears}y (Permissible Max: ${verdict.permissibleMaxAge}y)"
                    },
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SaffronPrimary
                )

                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
            }

            if (expanded && verdict.appliedRelaxationsEn.isNotEmpty()) {
                Surface(
                    color = Color(0xFFF8FAFC),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = if (isGujarati) "લાગુ થયેલ છૂટછાટો:" else "Applied Relaxations:",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF475569)
                        )
                        val rules = if (isGujarati) verdict.appliedRelaxationsGu else verdict.appliedRelaxationsEn
                        rules.forEach { r ->
                            Text(text = "• $r", fontSize = 10.sp, color = Color(0xFF334155))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Open Detail Button
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = AdminNavy),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = if (isGujarati) "અરજી તૈયારી અને ચેકલિસ્ટ" else "Prepare & Check",
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}
