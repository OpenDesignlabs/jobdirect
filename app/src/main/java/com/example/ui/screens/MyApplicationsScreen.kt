package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Launch
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.data.model.RecruitmentNotice
import com.example.ui.components.BoardPill
import com.example.ui.components.DeadlineBadge
import com.example.ui.theme.AdminNavy
import com.example.ui.theme.SaffronPrimary
import com.example.ui.theme.VerifiedEmerald
import com.example.ui.theme.VerifiedEmeraldContainer

@Composable
fun MyApplicationsScreen(
    savedApplications: List<SavedApplicationEntity>,
    allRecruitments: List<RecruitmentNotice>,
    isGujarati: Boolean,
    onSelectRecruitment: (String) -> Unit,
    onRemoveSaved: (String) -> Unit,
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isGu = isGujarati

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Tracker Header
        Surface(
            color = AdminNavy,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = if (isGu) "મારી સાચવેલી અરજીઓ અને OJAS ટ્રેકર" else "My Saved Applications & OJAS Tracker",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = if (isGu) "કન્ફર્મેશન નંબર, દસ્તાવેજ તૈયારી અને પરીક્ષા પ્રગતિ" else "Track OJAS Confirmation numbers, document readiness & stages",
                    fontSize = 11.sp,
                    color = Color(0xFF90CDF4)
                )
            }
        }

        if (savedApplications.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(54.dp)
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = if (isGu) "તમે હજુ સુધી કોઈ ભરતી સાચવી નથી" else "No applications tracked yet",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (isGu) {
                            "ભરતી લિસ્ટમાં જઈને 'બુકમાર્ક' બટન દબાવો જેથી દસ્તાવેજ ચેકલિસ્ટ અને OJAS નંબર સાચવી શકાય."
                        } else {
                            "Bookmark any Gujarat recruitment to track your document checklist and OJAS confirmation."
                        },
                        fontSize = 12.sp,
                        color = Color.LightGray,
                        lineHeight = 16.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Button(
                        onClick = onExploreClick,
                        colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Explore, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = if (isGu) "ભરતીઓ શોધો" else "Explore Openings")
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(savedApplications, key = { it.recruitmentId }) { item ->
                    val notice = allRecruitments.find { it.id == item.recruitmentId }
                    if (notice != null) {
                        TrackedApplicationCard(
                            savedEntity = item,
                            notice = notice,
                            isGujarati = isGu,
                            onCardClick = { onSelectRecruitment(notice.id) },
                            onRemove = { onRemoveSaved(notice.id) },
                            onCopyConfirmationNo = { no ->
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("OJAS Confirmation", no)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(
                                    context,
                                    if (isGu) "OJAS નંબર કોપી થયો: $no" else "Copied confirmation no: $no",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TrackedApplicationCard(
    savedEntity: SavedApplicationEntity,
    notice: RecruitmentNotice,
    isGujarati: Boolean,
    onCardClick: () -> Unit,
    onRemove: () -> Unit,
    onCopyConfirmationNo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val completedCount = savedEntity.completedDocumentIdsCommaSeparated
        .split(",")
        .count { it.isNotBlank() }

    val stageLabel = when (savedEntity.applicationStage) {
        "SAVED" -> if (isGujarati) "રસ ધરાવો છો (Saved)" else "Saved / Interested"
        "DOCS_READY" -> if (isGujarati) "દસ્તાવેજ તૈયાર (Docs Ready)" else "Documents Ready"
        "APPLIED_OJAS" -> if (isGujarati) "OJAS પર અરજી થયેલ" else "Applied on OJAS"
        "ADMIT_CARD" -> if (isGujarati) "કોલ લેટર ડાઉનલોડ થયેલ" else "Admit Card Downloaded"
        else -> savedEntity.applicationStage
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
            .testTag("tracked_card_${notice.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Row: Advt No, Board, Stage Badge, Delete
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = VerifiedEmeraldContainer,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = stageLabel,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F766E),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }

                    IconButton(onClick = onRemove, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove",
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Title
            Text(
                text = if (isGujarati) notice.titleGu else notice.titleEn,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // OJAS Confirmation Number Banner (if logged)
            if (savedEntity.ojasConfirmationNumber.isNotBlank()) {
                Surface(
                    color = Color(0xFFF0FDF4),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, VerifiedEmerald.copy(alpha = 0.4f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = if (isGujarati) "OJAS કન્ફર્મેશન નંબર:" else "OJAS Confirmation No:",
                                fontSize = 10.sp,
                                color = Color(0xFF15803D),
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = savedEntity.ojasConfirmationNumber,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF166534)
                            )
                        }

                        IconButton(
                            onClick = { onCopyConfirmationNo(savedEntity.ojasConfirmationNumber) },
                            modifier = Modifier.size(30.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy",
                                tint = VerifiedEmerald,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Readiness Checklist Progress Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isGujarati) "દસ્તાવેજ તૈયારી: $completedCount / 8 પૂર્ણ" else "Document Readiness: $completedCount / 8 Ready",
                    fontSize = 11.sp,
                    color = Color(0xFF475569),
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "${(completedCount * 100 / 8)}%",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = VerifiedEmerald
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            LinearProgressIndicator(
                progress = { completedCount.toFloat() / 8f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = VerifiedEmerald,
                trackColor = Color(0xFFE2E8F0)
            )

            // Notes if any
            if (savedEntity.candidateNotes.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    color = Color(0xFFF8FAFC),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "📝 ${savedEntity.candidateNotes}",
                        fontSize = 11.sp,
                        color = Color(0xFF334155),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Footer: Deadline badge & Action
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DeadlineBadge(
                    endDate = notice.applicationEndDate,
                    isExtended = notice.isDeadlineExtended,
                    isGujarati = isGujarati
                )

                Button(
                    onClick = onCardClick,
                    colors = ButtonDefaults.buttonColors(containerColor = AdminNavy),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (isGujarati) "ઓપન ચેકલિસ્ટ" else "Open Checklist",
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}
