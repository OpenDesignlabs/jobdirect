package com.example.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.data.model.CorrigendumNotice
import com.example.data.model.CorrigendumType
import com.example.data.model.RecruitmentBoard
import com.example.ui.components.BoardPill
import com.example.ui.theme.AdminNavy
import com.example.ui.theme.NoticeAmber
import com.example.ui.theme.NoticeAmberContainer
import com.example.ui.theme.OnNoticeAmberContainer
import com.example.ui.theme.SaffronPrimary

@Composable
fun CorrigendaScreen(
    corrigenda: List<CorrigendumNotice>,
    isGujarati: Boolean,
    onSelectRecruitment: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isGu = isGujarati

    var selectedTypeFilter by remember { mutableStateOf<CorrigendumType?>(null) }
    var selectedBoardFilter by remember { mutableStateOf<RecruitmentBoard?>(null) }

    val filteredList = remember(corrigenda, selectedTypeFilter, selectedBoardFilter) {
        corrigenda.filter { corr ->
            val matchType = selectedTypeFilter == null || corr.changeType == selectedTypeFilter
            val matchBoard = selectedBoardFilter == null || corr.board == selectedBoardFilter
            matchType && matchBoard
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Hero Notice
        Surface(
            color = NoticeAmberContainer,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.NotificationsActive,
                        contentDescription = null,
                        tint = NoticeAmber,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isGu) "સત્તાવાર સુધારા પત્રક ઓડિટ ટ્રેકર" else "Official Corrigenda & Change Tracker",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnNoticeAmberContainer
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (isGu) {
                        "GPSC, GSSSB અને પોલીસ બોર્ડ દ્વારા બહાર પાડવામાં આવેલ મુદત વધારો, જગ્યા ફેરફાર અને નિયમ સુધારાઓની ચકાસાયેલી વિગત."
                    } else {
                        "Source-verified audit trail of deadline extensions, revised vacancy quotas, and examination amendments."
                    },
                    fontSize = 12.sp,
                    color = OnNoticeAmberContainer.copy(alpha = 0.9f),
                    lineHeight = 16.sp
                )
            }
        }

        // Filters Header
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                // Change Type Filter Chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedTypeFilter == null,
                        onClick = { selectedTypeFilter = null },
                        label = {
                            Text(
                                text = if (isGu) "તમામ ફેરફારો" else "All Changes",
                                fontSize = 12.sp,
                                fontWeight = if (selectedTypeFilter == null) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AdminNavy,
                            selectedLabelColor = Color.White
                        )
                    )

                    CorrigendumType.values().forEach { type ->
                        val isSel = selectedTypeFilter == type
                        FilterChip(
                            selected = isSel,
                            onClick = { selectedTypeFilter = if (isSel) null else type },
                            label = {
                                Text(
                                    text = if (isGu) type.badgeLabelGu else type.badgeLabelEn,
                                    fontSize = 12.sp
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = NoticeAmber,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }
        }

        // Corrigenda Feed
        if (filteredList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isGu) "આ ફિલ્ટર માટે કોઈ સુધારા પત્રક નથી" else "No corrigenda for this filter",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredList, key = { it.id }) { item ->
                    CorrigendumCard(
                        corrigendum = item,
                        isGujarati = isGu,
                        onViewRecruitment = { onSelectRecruitment(item.recruitmentId) },
                        onViewNoticePdf = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.officialNoticePdfUrl))
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CorrigendumCard(
    corrigendum: CorrigendumNotice,
    isGujarati: Boolean,
    onViewRecruitment: () -> Unit,
    onViewNoticePdf: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("corrigendum_card_${corrigendum.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Advt No, Board, Issue Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BoardPill(board = corrigendum.board, isGujarati = isGujarati)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = corrigendum.advtNo,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = AdminNavy
                    )
                }

                Surface(
                    color = Color(0xFFF1F5F9),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = corrigendum.issueDate,
                        fontSize = 11.sp,
                        color = Color(0xFF64748B),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Corrigendum Official Number
            Text(
                text = corrigendum.corrigendumNo,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = SaffronPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Headline
            Text(
                text = if (isGujarati) corrigendum.headlineGu else corrigendum.headlineEn,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Summary
            Text(
                text = if (isGujarati) corrigendum.summaryGu else corrigendum.summaryEn,
                fontSize = 13.sp,
                color = Color(0xFF334155),
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Visual Diff Box (Original vs Revised)
            Surface(
                color = Color(0xFFF8FAFC),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = if (isGujarati) "ફેરફાર થયેલ બાબત:" else "Notice Difference / Diff:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF475569)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.Top) {
                        Text(
                            text = if (isGujarati) "અગાઉ: " else "Original: ",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFDC2626)
                        )
                        Text(
                            text = if (isGujarati) corrigendum.originalValueTextGu else corrigendum.originalValueTextEn,
                            fontSize = 11.sp,
                            color = Color(0xFF475569)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.Top) {
                        Text(
                            text = if (isGujarati) "સુધારેલ: " else "Revised: ",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF15803D)
                        )
                        Text(
                            text = if (isGujarati) corrigendum.revisedValueTextGu else corrigendum.revisedValueTextEn,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF15803D)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${if (isGujarati) "સંદર્ભ પેરા:" else "Ref Rule:"} ${corrigendum.affectedSectionOrRule}",
                        fontSize = 10.sp,
                        color = Color(0xFF64748B)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onViewNoticePdf,
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = AdminNavy
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isGujarati) "સુધારા PDF" else "Official PDF",
                        fontSize = 11.sp,
                        color = AdminNavy
                    )
                }

                Button(
                    onClick = onViewRecruitment,
                    colors = ButtonDefaults.buttonColors(containerColor = AdminNavy),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (isGujarati) "ભરતી વિગતો જુઓ" else "View Opening",
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
}
