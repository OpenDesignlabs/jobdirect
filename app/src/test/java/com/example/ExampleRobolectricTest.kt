package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.Corrigenda
import com.example.data.local.RecruitmentVacancy
import com.example.data.local.UserProfile
import com.example.data.model.CandidateCategory
import com.example.data.model.CandidateProfile
import com.example.data.model.EducationLevel
import com.example.data.model.EligibilityStatus
import com.example.data.model.Gender
import com.example.data.repository.VerifiedGujaratRecruitmentData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("JobDirect", appName)
  }

  @Test
  fun `verify gujarat recruitment eligibility calculations`() {
    val cceNotice = VerifiedGujaratRecruitmentData.verifiedRecruitments.find { it.id == "gsssb-cce-2026" }
    assertNotNull(cceNotice)

    // A candidate born in 1999 (27 years old in 2026) with Graduate degree
    val candidate = CandidateProfile(
      birthYear = 1999,
      birthMonth = 6,
      birthDay = 15,
      category = CandidateCategory.SEBC,
      gender = Gender.MALE,
      isGujaratDomicile = true,
      educationLevel = EducationLevel.GRADUATE
    )

    val verdict = VerifiedGujaratRecruitmentData.calculateEligibility(cceNotice!!, candidate)
    assertEquals(EligibilityStatus.ELIGIBLE, verdict.status)
    assertTrue(verdict.calculatedAgeYears in 26..28)
    assertEquals(40, verdict.permissibleMaxAge) // 35 base + 5 SEBC relaxation
  }

  @Test
  fun `verify Room entities for RecruitmentVacancy, Corrigenda, and UserProfile`() {
    val vacancy = RecruitmentVacancy(
      id = "test-vac-01",
      advtNo = "GPSC/202627/01",
      boardCode = "GPSC",
      titleEn = "Gujarat Administrative Service",
      titleGu = "ગુજરાત વહીવટી સેવા",
      postCadreEn = "Class 1",
      postCadreGu = "વર્ગ-૧",
      departmentEn = "General Administration",
      departmentGu = "સામાન્ય વહીવટ વિભાગ",
      totalVacancies = 345,
      vacanciesBreakdownEn = "Dy. Collector: 42",
      vacanciesBreakdownGu = "નાયબ કલેક્ટર: ૪૨",
      payScaleEn = "Level 10",
      payScaleGu = "લેવલ ૧૦",
      applicationStartDate = "2026-09-15",
      applicationEndDate = "2026-10-31",
      isDeadlineExtended = false,
      originalEndDate = null,
      examDateEstimated = "December 2026",
      cutoffDateForAge = "2026-10-31",
      minAge = 20,
      maxAgeGeneral = 35,
      educationLevelCode = "GRADUATE",
      educationDetailsEn = "Graduation in any stream",
      educationDetailsGu = "કોઈપણ પ્રવાહમાં સ્નાતક",
      isCccRequired = false,
      hasCorrigendum = true,
      corrigendumCount = 1,
      officialPdfUrl = "https://gpsc.gujarat.gov.in/notice.pdf",
      applyPortalUrl = "https://gpsc-ojas.gujarat.gov.in",
      lastVerifiedDate = "2026-10-15",
      verificationNotesEn = "Verified with GPSC gazette",
      verificationNotesGu = "GPSC ગેઝેટ મુજબ પ્રમાણિત",
      feeDetailsEn = "₹100 for General",
      feeDetailsGu = "સામાન્ય ઉમેદવારો માટે ₹૧૦૦"
    )
    assertEquals("test-vac-01", vacancy.id)
    assertEquals("GPSC", vacancy.boardCode)

    val corrigenda = Corrigenda(
      id = "corr-01",
      recruitmentId = "test-vac-01",
      advtNo = "GPSC/202627/01",
      boardCode = "GPSC",
      corrigendumNo = "Notice 1/2026",
      issueDate = "2026-10-14",
      changeTypeCode = "DEADLINE_EXTENSION",
      headlineEn = "Deadline Extended",
      headlineGu = "મુદત લંબાવી",
      summaryEn = "Application date extended to Nov 15",
      summaryGu = "અરજીની છેલ્લી તારીખ ૧૫-નવેમ્બર સુધી લંબાવી",
      originalValueTextEn = "31-Oct-2026",
      originalValueTextGu = "૩૧-ઓક્ટોબર-૨૦૨૬",
      revisedValueTextEn = "15-Nov-2026",
      revisedValueTextGu = "૧૫-નવેમ્બર-૨૦૨૬",
      affectedSectionOrRule = "Rule 4",
      officialNoticePdfUrl = "https://gpsc.gujarat.gov.in/corr.pdf"
    )
    assertEquals("DEADLINE_EXTENSION", corrigenda.changeTypeCode)

    val userProfile = UserProfile(
      id = 1,
      birthYear = 1998,
      birthMonth = 8,
      birthDay = 20,
      categoryCode = "SEBC",
      genderCode = "MALE",
      isGujaratDomicile = true,
      educationLevelCode = "GRADUATE",
      isGujaratiPreferred = true
    )
    assertEquals(1998, userProfile.birthYear)
    assertTrue(userProfile.isGujaratiPreferred)
  }
}
