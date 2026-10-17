package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CorrigendumType
import com.example.data.model.RecruitmentBoard
import com.example.ui.AppScreen
import com.example.ui.theme.AdminNavy
import com.example.ui.theme.NoticeAmber
import com.example.ui.theme.NoticeAmberContainer
import com.example.ui.theme.OnNoticeAmberContainer
import com.example.ui.theme.OnSaffronContainer
import com.example.ui.theme.OnVerifiedEmeraldContainer
import com.example.ui.theme.SaffronContainer
import com.example.ui.theme.SaffronPrimary
import com.example.ui.theme.UrgentRed
import com.example.ui.theme.UrgentRedContainer
import com.example.ui.theme.VerifiedEmerald
import com.example.ui.theme.VerifiedEmeraldContainer

@Composable
fun JobDirectTopBar(
    isGujarati: Boolean,
    onToggleLanguage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = AdminNavy,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(SaffronPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isGujarati) "ભરતી" else "JD",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = if (isGujarati) "જોબડાયરેક્ટ ગુજરાત" else "JobDirect Gujarat",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 17.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = if (isGujarati) "સત્તાવાર ભરતી ઇન્ટેલિજન્સ અને પાત્રતા" else "Official Recruitment Intelligence",
                        color = Color(0xFF90CDF4),
                        fontSize = 11.sp,
                        maxLines = 1
                    )
                }
            }

            // Language Toggle Switcher Button
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { onToggleLanguage() }
                    .testTag("language_toggle_btn"),
                color = if (isGujarati) SaffronPrimary else Color(0xFF1E3A5F),
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Translate,
                        contentDescription = "Switch Language",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isGujarati) "ગુજરાતી" else "English",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun JobDirectBottomBar(
    currentScreen: AppScreen,
    isGujarati: Boolean,
    corrigendaCount: Int,
    savedCount: Int,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = currentScreen == AppScreen.RECRUITMENTS || currentScreen == AppScreen.DETAIL_VIEW,
            onClick = { onNavigate(AppScreen.RECRUITMENTS) },
            icon = {
                Icon(
                    imageVector = if (currentScreen == AppScreen.RECRUITMENTS) Icons.Filled.Description else Icons.Outlined.Description,
                    contentDescription = "Recruitments"
                )
            },
            label = {
                Text(
                    text = if (isGujarati) "ભરતીઓ" else "Jobs",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = SaffronPrimary,
                selectedTextColor = SaffronPrimary,
                indicatorColor = SaffronContainer
            ),
            modifier = Modifier.testTag("nav_recruitments")
        )

        NavigationBarItem(
            selected = currentScreen == AppScreen.CORRIGENDA,
            onClick = { onNavigate(AppScreen.CORRIGENDA) },
            icon = {
                BadgedBox(
                    badge = {
                        if (corrigendaCount > 0) {
                            Badge(containerColor = NoticeAmber) {
                                Text(text = "$corrigendaCount", color = Color.White, fontSize = 10.sp)
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (currentScreen == AppScreen.CORRIGENDA) Icons.Filled.NotificationsActive else Icons.Outlined.Notifications,
                        contentDescription = "Corrigenda"
                    )
                }
            },
            label = {
                Text(
                    text = if (isGujarati) "સુધારા નોટિસ" else "Corrigenda",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NoticeAmber,
                selectedTextColor = NoticeAmber,
                indicatorColor = NoticeAmberContainer
            ),
            modifier = Modifier.testTag("nav_corrigenda")
        )

        NavigationBarItem(
            selected = currentScreen == AppScreen.ELIGIBILITY_CALCULATOR,
            onClick = { onNavigate(AppScreen.ELIGIBILITY_CALCULATOR) },
            icon = {
                Icon(
                    imageVector = if (currentScreen == AppScreen.ELIGIBILITY_CALCULATOR) Icons.Filled.Calculate else Icons.Outlined.Calculate,
                    contentDescription = "Eligibility"
                )
            },
            label = {
                Text(
                    text = if (isGujarati) "પાત્રતા કેલ્ક" else "Eligibility",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AdminNavy,
                selectedTextColor = AdminNavy,
                indicatorColor = Color(0xFFD0E4FF)
            ),
            modifier = Modifier.testTag("nav_eligibility")
        )

        NavigationBarItem(
            selected = currentScreen == AppScreen.MY_APPLICATIONS,
            onClick = { onNavigate(AppScreen.MY_APPLICATIONS) },
            icon = {
                BadgedBox(
                    badge = {
                        if (savedCount > 0) {
                            Badge(containerColor = VerifiedEmerald) {
                                Text(text = "$savedCount", color = Color.White, fontSize = 10.sp)
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (currentScreen == AppScreen.MY_APPLICATIONS) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = "Tracker"
                    )
                }
            },
            label = {
                Text(
                    text = if (isGujarati) "મારી અરજીઓ" else "Tracker",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = VerifiedEmerald,
                selectedTextColor = VerifiedEmerald,
                indicatorColor = VerifiedEmeraldContainer
            ),
            modifier = Modifier.testTag("nav_tracker")
        )
    }
}

@Composable
fun BoardPill(board: RecruitmentBoard, isGujarati: Boolean) {
    val (bg, fg) = when (board) {
        RecruitmentBoard.GPSC -> Color(0xFFE0F2FE) to Color(0xFF0369A1)
        RecruitmentBoard.GSSSB -> Color(0xFFFEF3C7) to Color(0xFFB45309)
        RecruitmentBoard.POLICE -> Color(0xFFFEE2E2) to Color(0xFFB91C1C)
        RecruitmentBoard.GPSSB -> Color(0xFFDCFCE7) to Color(0xFF15803D)
        RecruitmentBoard.HIGH_COURT -> Color(0xFFF3E8FF) to Color(0xFF6B21A8)
        RecruitmentBoard.POWER_CORP -> Color(0xFFFFEDD5) to Color(0xFFC2410C)
        RecruitmentBoard.ALL -> Color(0xFFF1F5F9) to Color(0xFF334155)
    }

    Surface(
        color = bg,
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = board.code,
            color = fg,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}

@Composable
fun CorrigendumBadge(
    isGujarati: Boolean,
    count: Int = 1,
    modifier: Modifier = Modifier
) {
    Surface(
        color = NoticeAmberContainer,
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = NoticeAmber,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = if (isGujarati) "સુધારો ($count)" else "Corrigendum ($count)",
                color = OnNoticeAmberContainer,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DeadlineBadge(
    endDate: String,
    isExtended: Boolean,
    isGujarati: Boolean,
    modifier: Modifier = Modifier
) {
    val bg = if (isExtended) NoticeAmberContainer else Color(0xFFF1F5F9)
    val fg = if (isExtended) OnNoticeAmberContainer else Color(0xFF475569)

    Surface(
        color = bg,
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
        ) {
            Text(
                text = if (isExtended) {
                    if (isGujarati) "મુદત લંબાવી: $endDate" else "Extended: $endDate"
                } else {
                    if (isGujarati) "છેલ્લી તારીખ: $endDate" else "Closing: $endDate"
                },
                color = fg,
                fontSize = 11.sp,
                fontWeight = if (isExtended) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}
