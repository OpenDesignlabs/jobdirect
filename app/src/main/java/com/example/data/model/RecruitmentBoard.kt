package com.example.data.model

enum class RecruitmentBoard(
    val code: String,
    val nameEn: String,
    val nameGu: String,
    val portalName: String,
    val portalUrl: String
) {
    ALL(
        code = "ALL",
        nameEn = "All Gujarat Boards",
        nameGu = "તમામ ગુજરાત બોર્ડ",
        portalName = "State Portals",
        portalUrl = "https://ojas.gujarat.gov.in"
    ),
    GPSC(
        code = "GPSC",
        nameEn = "Gujarat Public Service Commission",
        nameGu = "ગુજરાત જાહેર સેવા આયોગ (GPSC)",
        portalName = "GPSC OJAS",
        portalUrl = "https://gpsc-ojas.gujarat.gov.in"
    ),
    GSSSB(
        code = "GSSSB",
        nameEn = "Gujarat Subordinate Service Selection Board",
        nameGu = "ગુજરાત ગૌણ સેવા પસંદગી મંડળ (GSSSB)",
        portalName = "GSSSB OJAS",
        portalUrl = "https://gsssb.gujarat.gov.in"
    ),
    POLICE(
        code = "POLICE",
        nameEn = "Gujarat Police Recruitment Board (LRB/PSI)",
        nameGu = "પોલીસ ભરતી બોર્ડ (LRB / PSI)",
        portalName = "LRB Gujarat",
        portalUrl = "https://lrbgujarat2026.in"
    ),
    GPSSB(
        code = "GPSSB",
        nameEn = "Gujarat Panchayat Service Selection Board",
        nameGu = "ગુજરાત પંચાયત સેવા પસંદગી બોર્ડ (GPSSB)",
        portalName = "GPSSB Portal",
        portalUrl = "https://gpssb.gujarat.gov.in"
    ),
    HIGH_COURT(
        code = "HC_GUJ",
        nameEn = "High Court of Gujarat",
        nameGu = "ગુજરાત વડી અદાલત (હાઇકોર્ટ)",
        portalName = "HC OJAS",
        portalUrl = "https://hc-ojas.gujarat.gov.in"
    ),
    POWER_CORP(
        code = "POWER",
        nameEn = "Gujarat Urja Vikas Nigam (GETCO/DISCOMs)",
        nameGu = "ગુજરાત ઊર્જા વિકાસ નિગમ (GETCO/MGVCL)",
        portalName = "GUVNL Careers",
        portalUrl = "https://www.guvnl.com"
    )
}

enum class EducationLevel(
    val labelEn: String,
    val labelGu: String
) {
    ANY("Any Qualification", "કોઈપણ લાયકાત"),
    SSC_10TH("10th Pass (SSC)", "ધોરણ ૧૦ પાસ"),
    HSC_12TH("12th Pass (HSC)", "ધોરણ ૧૨ પાસ"),
    GRADUATE("Bachelor's Degree (Any)", "સ્નાતક (ગ્રેજ્યુએટ)"),
    ENGINEERING("Diploma / B.E. / B.Tech", "ઇજનેરી (ડિપ્લોમા/ડિગ્રી)"),
    COMMERCE_CA("B.Com / M.Com / Inter CA", "કોમર્સ / હિસાબી શાખા"),
    MEDICAL_NURSING("MBBS / BAMS / GNM", "તબીબી / નર્સિંગ"),
    LAW("LL.B. / Legal Degree", "કાયદા સ્નાતક (LL.B.)")
}

enum class CandidateCategory(
    val code: String,
    val labelEn: String,
    val labelGu: String,
    val defaultMaleRelaxationYears: Int,
    val defaultFemaleRelaxationYears: Int
) {
    GENERAL("GEN", "General / Open", "સામાન્ય (બિનઅનામત)", 0, 5),
    EWS("EWS", "Economically Weaker Section (EWS)", "આર્થિક નબળા વર્ગ (EWS)", 5, 10),
    SEBC("SEBC", "Socially & Educationally Backward Class (OBC)", "સામાજિક અને શૈક્ષણિક પછાત વર્ગ (SEBC/OBC)", 5, 10),
    SC("SC", "Scheduled Caste (SC)", "અનુસૂચિત જાતિ (SC)", 5, 10),
    ST("ST", "Scheduled Tribe (ST)", "અનુસૂચિત જનજાતિ (ST)", 5, 10)
}

enum class Gender(val labelEn: String, val labelGu: String) {
    MALE("Male", "પુરુષ"),
    FEMALE("Female", "મહિલા")
}
