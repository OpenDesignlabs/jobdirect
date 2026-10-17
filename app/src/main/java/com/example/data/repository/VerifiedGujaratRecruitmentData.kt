package com.example.data.repository

import com.example.data.model.CandidateCategory
import com.example.data.model.CandidateProfile
import com.example.data.model.CorrigendumNotice
import com.example.data.model.CorrigendumType
import com.example.data.model.DocumentChecklistItem
import com.example.data.model.EducationLevel
import com.example.data.model.EligibilityStatus
import com.example.data.model.EligibilityVerdict
import com.example.data.model.Gender
import com.example.data.model.RecruitmentBoard
import com.example.data.model.RecruitmentNotice

object VerifiedGujaratRecruitmentData {

    val standardChecklistItems = listOf(
        DocumentChecklistItem(
            id = "photo",
            nameEn = "Passport Size Photograph (Recent)",
            nameGu = "પાસપોર્ટ સાઇઝનો તાજેતરનો ફોટોગ્રાફ",
            specsEn = "JPG / JPEG format, Max 15 KB size, Dimensions 5 cm x 3.6 cm, White background preferred.",
            specsGu = "JPG ફોર્મેટ, વધુમાં વધુ 15 KB સાઇઝ, 5 cm x 3.6 cm, સફેદ બેકગ્રાઉન્ડ જરૂરી.",
            applicableCategories = listOf("ALL"),
            isMandatoryAtOjasApply = true,
            ojasInstructionEn = "OJAS will reject files exceeding 15KB. Use standard photo compressor before uploading.",
            ojasInstructionGu = "૧૫ KB થી મોટી ફાઇલ OJAS સ્વીકારશે નહીં. અપલોડ કરતાં પહેલાં સાઇઝ ચકાસો."
        ),
        DocumentChecklistItem(
            id = "signature",
            nameEn = "Candidate Signature Scan",
            nameGu = "ઉમેદવારની સહીનું સ્કેન",
            specsEn = "JPG / JPEG format, Max 15 KB size, Dimensions 2.5 cm x 7.5 cm, Black/Blue ball pen on white paper.",
            specsGu = "JPG ફોર્મેટ, વધુમાં વધુ 15 KB સાઇઝ, 2.5 cm x 7.5 cm, સફેદ કાગળ પર કાળી/વાદળી પેનથી સહી.",
            applicableCategories = listOf("ALL"),
            isMandatoryAtOjasApply = true,
            ojasInstructionEn = "Crop tightly around signature to prevent aspect ratio distortion on the admit card.",
            ojasInstructionGu = "હોલ ટિકિટ પર સહી યોગ્ય દેખાય તે માટે સહી ફરતે વધારાની જગ્યા ક્રોપ કરો."
        ),
        DocumentChecklistItem(
            id = "ncl_certificate",
            nameEn = "Non-Creamy Layer Certificate (Parishisth-K)",
            nameGu = "નોન-ક્રીમીલેયર પ્રમાણપત્ર (પરિશિષ્ટ-ક)",
            specsEn = "Valid for 3 financial years as per Gujarat Govt rules. Check issue date on the certificate.",
            specsGu = "ગુજરાત સરકારના ઠરાવ મુજબ ૩ નાણાકીય વર્ષ માટે માન્ય. પરિશિષ્ટ-ક (ગુજરાતી) હોવું ફરજિયાત.",
            applicableCategories = listOf("SEBC"),
            isMandatoryAtOjasApply = true,
            ojasInstructionEn = "Enter exact certificate number and date of issue on OJAS application form.",
            ojasInstructionGu = "OJAS ફોર્મમાં પ્રમાણપત્ર ક્રમાંક અને જારી કર્યાની તારીખ અચૂક દાખલ કરો."
        ),
        DocumentChecklistItem(
            id = "ews_certificate",
            nameEn = "EWS Eligibility Certificate (Parishisth-G)",
            nameGu = "આર્થિક નબળા વર્ગ (EWS) પ્રમાણપત્ર (પરિશિષ્ટ-ગ)",
            specsEn = "Valid for 3 years from date of issue as per Gujarat State Social Justice Dept.",
            specsGu = "જારી થયા તારીખથી ૩ વર્ષની માન્યતા ધરાવતું પરિશિષ્ટ-ગ પ્રમાણપત્ર.",
            applicableCategories = listOf("EWS"),
            isMandatoryAtOjasApply = true,
            ojasInstructionEn = "Certificate must be issued prior to the closing date of application.",
            ojasInstructionGu = "અરજી કરવાની છેલ્લી તારીખ પહેલાં પ્રમાણપત્ર નીકળેલું હોવું જોઈએ."
        ),
        DocumentChecklistItem(
            id = "caste_certificate",
            nameEn = "SC / ST Caste Certificate",
            nameGu = "અનુસૂચિત જાતિ / જનજાતિ જાતિ પ્રમાણપત્ર",
            specsEn = "Issued by competent authority (Mamlatdar / TDO / Dy Collector) with valid Barcode.",
            specsGu = "સક્ષમ અધિકારી દ્વારા અપાયેલ બારકોડ વાળું જાતિનું પ્રમાણપત્ર.",
            applicableCategories = listOf("SC", "ST"),
            isMandatoryAtOjasApply = true,
            ojasInstructionEn = "Keep digital copy ready for document verification stage.",
            ojasInstructionGu = "ડોક્યુમેન્ટ વેરિફિકેશન સમયે અસલ પ્રમાણપત્ર રજૂ કરવાનું રહેશે."
        ),
        DocumentChecklistItem(
            id = "ccc_certificate",
            nameEn = "C.C.C. Computer Knowledge Certificate",
            nameGu = "સી.સી.સી. (CCC) કોમ્પ્યુટર પ્રમાણપત્ર",
            specsEn = "From recognized University / SPIPA / ITI / Gujarat University or 10th/12th Computer subject.",
            specsGu = "માન્ય યુનિવર્સિટી, સ્પિપા (SPIPA) અથવા ધોરણ ૧૦/૧૨ માં કોમ્પ્યુટર વિષય પાસનું પ્રમાણપત્ર.",
            applicableCategories = listOf("ALL"),
            isMandatoryAtOjasApply = false,
            ojasInstructionEn = "If selected, submission is mandatory before appointment/training probation.",
            ojasInstructionGu = "અરજી વખતે 'હા' દર્શાવવું અને નિમણૂક પહેલાં પ્રમાણપત્ર સબમિટ કરવું જરૂરી."
        ),
        DocumentChecklistItem(
            id = "degree_certificate",
            nameEn = "Graduation Degree & Final Semester Marksheet",
            nameGu = "સ્નાતક ડિગ્રી પ્રમાણપત્ર અને છેલ્લા સેમેસ્ટર માર્કશીટ",
            specsEn = "From UGC / AICTE recognized university. CGPA to Percentage conversion formula certificate if applicable.",
            specsGu = "UGC માન્ય યુનિવર્સિટીનું સ્નાતક ડિગ્રી સર્ટિફિકેટ અને ટકાવારી ગણતરી પત્રક.",
            applicableCategories = listOf("ALL"),
            isMandatoryAtOjasApply = true,
            ojasInstructionEn = "Enter total obtained marks, total marks, percentage, and trial count accurately.",
            ojasInstructionGu = "કુલ ગુણ, મેળવેલ ગુણ, ટકાવારી અને ટ્રાયલ સંખ્યા ચોક્કસ ભરો."
        ),
        DocumentChecklistItem(
            id = "domicile",
            nameEn = "Gujarat Domicile / Birth Certificate",
            nameGu = "ગુજરાત ડોમિસાઇલ / જન્મનું પ્રમાણપત્ર",
            specsEn = "Proof of residence in Gujarat for category reservations & women reservation quota.",
            specsGu = "ગુજરાતના રહેવાસી હોવાના પુરાવા તરીકે અનામત અને વયમર્યાદા છૂટછાટ માટે જરૂરી.",
            applicableCategories = listOf("ALL"),
            isMandatoryAtOjasApply = false,
            ojasInstructionEn = "Required during Document Verification (DV) stage.",
            ojasInstructionGu = "રૂબરૂ ચકાસણી સમયે સક્ષમ અધિકારી સમક્ષ રજૂ કરવું."
        )
    )

    val verifiedRecruitments = listOf(
        RecruitmentNotice(
            id = "gsssb-cce-2026",
            advtNo = "GSSSB/202627/212",
            board = RecruitmentBoard.GSSSB,
            titleEn = "Gujarat Combined Competitive Examination (CCE) Group A & Group B",
            titleGu = "ગુજરાત ગૌણ સેવા સંયુક્ત સ્પર્ધાત્મક પરીક્ષા (CCE) ગ્રૂપ-A અને ગ્રૂપ-B",
            postCadreEn = "Class 3 Non-Gazetted",
            postCadreGu = "વર્ગ-૩ બિન-રાજપત્રિત",
            departmentEn = "General Administration Dept, Sachivalaya & Field Offices",
            departmentGu = "સામાન્ય વહીવટ વિભાગ, સચિવાલય અને ખાતાના વડાની કચેરીઓ",
            totalVacancies = 5554,
            vacanciesBreakdownEn = "Group A (Head Clerk, Senior Clerk, Office Asst): 2,120 | Group B (Junior Clerk): 3,434",
            vacanciesBreakdownGu = "ગ્રૂપ A (હેડ ક્લાર્ક, સિનિયર ક્લાર્ક, ઓફિસ આસિસ્ટન્ટ): ૨,૧૨૦ | ગ્રૂપ B (જુનિયર ક્લાર્ક): ૩,૪૩૪",
            payScaleEn = "Level 2 to Level 5 (Fix pay ₹26,000 to ₹40,800 / month)",
            payScaleGu = "લેવલ ૨ થી લેવલ ૫ (ફિક્સ પગાર ₹૨૬,૦૦૦ થી ₹૪૦,૮૦૦ / માસિક)",
            applicationStartDate = "2026-10-01",
            applicationEndDate = "2026-11-15",
            isDeadlineExtended = true,
            originalEndDate = "2026-10-25",
            examDateEstimated = "January 2027 (CBRT Prelims)",
            cutoffDateForAge = "2026-11-15",
            minAge = 20,
            maxAgeGeneral = 35,
            educationLevel = EducationLevel.GRADUATE,
            educationDetailsEn = "Bachelor's Degree in any discipline from a recognized University or equivalent.",
            educationDetailsGu = "માન્ય યુનિવર્સિટીમાંથી કોઈપણ પ્રવાહમાં સ્નાતક (ગ્રેજ્યુએટ) ની પદવી.",
            isCccRequired = true,
            hasCorrigendum = true,
            corrigendumCount = 2,
            officialPdfUrl = "https://gsssb.gujarat.gov.in/notices/CCE_202627_212_Detailed.pdf",
            applyPortalUrl = "https://ojas.gujarat.gov.in",
            lastVerifiedDate = "2026-10-15",
            verificationNotesEn = "Verified with GSSSB Corrigendum Notice No. 2 dated 14-Oct-2026. Application closing date extended to 15-Nov-2026.",
            verificationNotesGu = "૧૪-ઓક્ટોબર-૨૦૨૬ ના રોજ પ્રસિદ્ધ સુધારા પત્રક ક્રમાંક-૨ મુજબ અરજી કરવાની મુદત ૧૫-નવેમ્બર સુધી લંબાવેલ છે.",
            feeDetailsEn = "General/Unreserved: ₹100 + Charges. SC/ST/SEBC/EWS/Ex-Servicemen: Exempted.",
            feeDetailsGu = "સામાન્ય કેટેગરી: ₹૧૦૦ + ચાર્જીસ. અનામત વર્ગ અને મહિલા ઉમેદવારો માટે ફી માફી.",
            stagesEn = listOf("Stage 1: Preliminary Exam (CBRT 100 Marks)", "Stage 2: Mains Exam (Descriptive for Group A, Objective for Group B)", "Stage 3: Document Verification"),
            stagesGu = listOf("તબક્કો ૧: પ્રાથમિક કોમ્પ્યુટર આધારિત પરીક્ષા (CBRT ૧૦૦ ગુણ)", "તબક્કો ૨: મુખ્ય પરીક્ષા (ગ્રૂપ A વર્ણનાત્મક, ગ્રૂપ B હેતુલક્ષી)", "તબક્કો ૩: અસલ પ્રમાણપત્ર ચકાસણી"),
            requiredDocumentIds = listOf("photo", "signature", "ncl_certificate", "ews_certificate", "caste_certificate", "ccc_certificate", "degree_certificate")
        ),
        RecruitmentNotice(
            id = "gpsc-class-1-2-2026",
            advtNo = "GPSC/202627/01",
            board = RecruitmentBoard.GPSC,
            titleEn = "Gujarat Administrative Service (Class 1) & Gujarat Civil Service (Class 1 & 2)",
            titleGu = "ગુજરાત વહીવટી સેવા (વર્ગ-૧) અને ગુજરાત મુલ્કી સેવા (વર્ગ-૧ અને વર્ગ-૨)",
            postCadreEn = "Class 1 & Class 2 Gazetted Officers",
            postCadreGu = "વર્ગ-૧ અને વર્ગ-૨ ગેઝેટેડ અધિકારીઓ",
            departmentEn = "Revenue, Home, Finance, Rural Development, Tribal Affairs",
            departmentGu = "મહેસૂલ, ગૃહ, નાણાં, પંચાયત અને ગ્રામ વિકાસ વિભાગ",
            totalVacancies = 345,
            vacanciesBreakdownEn = "Dy. Collector (Class 1): 42 | Dy. SP (Class 1): 28 | DDO/Mamlatdar (Class 2): 175 | TDO (Class 2): 100",
            vacanciesBreakdownGu = "નાયબ કલેક્ટર: ૪૨ | નાયબ પોલીસ અધિક્ષક (DySP): ૨૮ | મામલતદાર: ૧૭૫ | તાલુકા વિકાસ અધિકારી (TDO): ૧૦૦",
            payScaleEn = "Pay Matrix Level 8 (₹44,900 - ₹1,42,400) to Level 10 (₹56,100 - ₹1,77,500)",
            payScaleGu = "પે મેટ્રિક્સ લેવલ ૮ (₹૪૪,૯૦૦) થી લેવલ ૧૦ (₹૫૬,૧૦૦ - ₹૧,૭૭,૫૦૦)",
            applicationStartDate = "2026-09-15",
            applicationEndDate = "2026-10-31",
            isDeadlineExtended = false,
            originalEndDate = null,
            examDateEstimated = "December 20, 2026 (Prelims)",
            cutoffDateForAge = "2026-10-31",
            minAge = 20,
            maxAgeGeneral = 35,
            educationLevel = EducationLevel.GRADUATE,
            educationDetailsEn = "Graduate degree in any stream. Candidates appearing in final semester are also eligible to apply.",
            educationDetailsGu = "કોઈપણ શાખામાં સ્નાતકની પદવી. છેલ્લા સેમેસ્ટરમાં અભ્યાસ કરતા વિદ્યાર્થીઓ પણ અરજી કરી શકે.",
            isCccRequired = false,
            hasCorrigendum = true,
            corrigendumCount = 1,
            officialPdfUrl = "https://gpsc.gujarat.gov.in/advertisement-detail?advt=01202627",
            applyPortalUrl = "https://gpsc-ojas.gujarat.gov.in",
            lastVerifiedDate = "2026-10-12",
            verificationNotesEn = "Verified with GPSC Notification. Prelims test center allocation updated.",
            verificationNotesGu = "GPSC સત્તાવાર જાહેરખબર મુજબ પ્રમાણિત. પ્રાથમિક પરીક્ષા ૨૦ ડિસેમ્બરે યોજાશે.",
            feeDetailsEn = "₹100 for General category male candidates. All women and reserved candidates exempted.",
            feeDetailsGu = "સામાન્ય વર્ગના પુરુષ ઉમેદવારો માટે ₹૧૦૦. તમામ મહિલાઓ અને અનામત વર્ગો માટે મફત.",
            stagesEn = listOf("Preliminary Exam (2 Papers - 400 Marks)", "Main Written Examination (6 Papers - 900 Marks)", "Personality Test / Interview (100 Marks)"),
            stagesGu = listOf("પ્રાથમિક કસોટી (૨ પેપર - ૪૦૦ ગુણ)", "મુખ્ય લેખિત પરીક્ષા (૬ પેપર - ૯૦૦ ગુણ)", "મૌખિક ઇન્ટરવ્યુ (૧૦૦ ગુણ)"),
            requiredDocumentIds = listOf("photo", "signature", "degree_certificate", "ncl_certificate", "ews_certificate", "caste_certificate", "domicile")
        ),
        RecruitmentNotice(
            id = "police-lrb-constable-2026",
            advtNo = "LRB/202627/01",
            board = RecruitmentBoard.POLICE,
            titleEn = "Gujarat Police Lokrakshak (Unarmed & Armed Police Constable, SRPF, Jail Sepoy)",
            titleGu = "ગુજરાત પોલીસ લોકરક્ષક ભરતી (બિન-હથિયારી/હથિયારી કોન્સ્ટેબલ, SRPF અને જેલ સિપાહી)",
            postCadreEn = "Class 3 Police Personnel",
            postCadreGu = "વર્ગ-૩ પોલીસ સંવર્ગ",
            departmentEn = "Home Department, Government of Gujarat",
            departmentGu = "ગૃહ વિભાગ, ગુજરાત સરકાર",
            totalVacancies = 12472,
            vacanciesBreakdownEn = "Unarmed Constable: 5,600 | Armed Constable: 3,800 | SRPF: 2,400 | Jail Sepoy: 672",
            vacanciesBreakdownGu = "બિન-હથિયારી કોન્સ્ટેબલ: ૫,૬૦૦ | હથિયારી કોન્સ્ટેબલ: ૩,૮૦૦ | એસ.આર.પી.એફ: ૨,૪૦૦ | જેલ સિપાહી: ૬૭૨",
            payScaleEn = "Fix pay ₹26,000 / month for initial 5 years, then Level 2 (₹19,900 - ₹63,200)",
            payScaleGu = "પ્રથમ ૫ વર્ષ ફિક્સ પગાર ₹૨૬,૦૦૦/માસિક, ત્યારબાદ નિયમિત પગાર સ્કેલ ₹૧૯,૯૦૦ થી ₹૬૩,૨૦૦",
            applicationStartDate = "2026-10-05",
            applicationEndDate = "2026-11-20",
            isDeadlineExtended = true,
            originalEndDate = "2026-11-05",
            examDateEstimated = "Physical Test (PET/PST): Jan-Feb 2027",
            cutoffDateForAge = "2026-11-20",
            minAge = 18,
            maxAgeGeneral = 33,
            educationLevel = EducationLevel.HSC_12TH,
            educationDetailsEn = "12th Standard Passed (HSC) from Gujarat Secondary & Higher Secondary Board or equivalent.",
            educationDetailsGu = "ગુજરાત માધ્યમિક અને ઉચ્ચતર માધ્યમિક શિક્ષણ બોર્ડમાંથી ધોરણ ૧૨ પાસ અથવા સમકક્ષ.",
            isCccRequired = true,
            hasCorrigendum = true,
            corrigendumCount = 3,
            officialPdfUrl = "https://lrbgujarat2026.in/pdf/LRB_Rule_Amendment_2026.pdf",
            applyPortalUrl = "https://ojas.gujarat.gov.in",
            lastVerifiedDate = "2026-10-16",
            verificationNotesEn = "PET marks removed from merit as per revised Home Dept rules. Physical is now qualifying only; written test is 200 marks.",
            verificationNotesGu = "ગૃહ વિભાગના નવા નિયમ મુજબ શારીરિક કસોટી માત્ર ક્વોલિફાઇંગ રહેશે. મેરિટ લેખિત પરીક્ષાના આધારે બનશે.",
            feeDetailsEn = "₹100 for General male. SC/ST/SEBC/EWS/Female candidates free.",
            feeDetailsGu = "સામાન્ય વર્ગના પુરુષો માટે ₹૧૦૦. મહિલાઓ અને અનામત ઉમેદવારો માટે વિનામૂલ્યે.",
            stagesEn = listOf("Physical Efficiency Test (PET - 5000m run in 25 min)", "Physical Standard Test (PST - Height & Chest measurement)", "Objective Written Test (200 Marks / 3 Hours)", "Medical Examination"),
            stagesGu = listOf("શારીરિક ક્ષમતા કસોટી (૫૦૦૦ મીટર દોડ ૨૫ મિનિટમાં)", "શારીરિક માપણી કસોટી (ઊંચાઈ અને છાતીનું માપ)", "હેતુલક્ષી લેખિત પરીક્ષા (૨૦૦ ગુણ / ૩ કલાક)", "તબીબી તપાસણી"),
            requiredDocumentIds = listOf("photo", "signature", "ncl_certificate", "ews_certificate", "caste_certificate", "domicile")
        ),
        RecruitmentNotice(
            id = "police-psi-cadre-2026",
            advtNo = "PSIRB/202627/01",
            board = RecruitmentBoard.POLICE,
            titleEn = "Police Sub Inspector (Unarmed PSI, Armed PSI, Platoon Commander, Intelligence Officer)",
            titleGu = "પોલીસ સબ ઇન્સ્પેક્ટર (બિન-હથિયારી PSI, હથિયારી PSI, પ્લાટૂન કમાન્ડર, ઇન્ટેલિજન્સ ઓફિસર)",
            postCadreEn = "Class 3 Police Executive",
            postCadreGu = "વર્ગ-૩ પોલીસ કાર્યકારી",
            departmentEn = "Director General of Police Office, Gujarat State",
            departmentGu = "પોલીસ મહાનિર્દેશક કચેરી, ગુજરાત રાજ્ય",
            totalVacancies = 824,
            vacanciesBreakdownEn = "Unarmed PSI: 472 | Armed PSI: 156 | IO (Intelligence): 88 | Platoon Commander: 108",
            vacanciesBreakdownGu = "બિન-હથિયારી PSI: ૪૭૨ | હથિયારી PSI: ૧૫૬ | ઇન્ટેલિજન્સ ઓફિસર: ૮૮ | પ્લાટૂન કમાન્ડર: ૧૦૮",
            payScaleEn = "Fix pay ₹40,800 / month for 5 years, then Level 7 (₹39,900 - ₹1,26,600)",
            payScaleGu = "પ્રથમ ૫ વર્ષ ફિક્સ પગાર ₹૪૦,૮૦૦, ત્યારબાદ લેવલ-૭ (₹૩૯,૯૦૦ થી ₹૧,૨૬,૬૦૦)",
            applicationStartDate = "2026-10-10",
            applicationEndDate = "2026-11-25",
            isDeadlineExtended = false,
            originalEndDate = null,
            examDateEstimated = "Physical: February 2027 | Written: April 2027",
            cutoffDateForAge = "2026-11-25",
            minAge = 21,
            maxAgeGeneral = 35,
            educationLevel = EducationLevel.GRADUATE,
            educationDetailsEn = "Graduation in any stream from recognized university. Height: 165 cm for General Male, 158 cm for Female.",
            educationDetailsGu = "કોઈપણ પ્રવાહમાં સ્નાતક. ઊંચાઈ: સામાન્ય પુરુષ ૧૬૫ સે.મી., મહિલા ૧૫૮ સે.મી.",
            isCccRequired = true,
            hasCorrigendum = true,
            corrigendumCount = 1,
            officialPdfUrl = "https://psirbgujarat2026.in/rules/PSI_Cadre_Rules.pdf",
            applyPortalUrl = "https://ojas.gujarat.gov.in",
            lastVerifiedDate = "2026-10-14",
            verificationNotesEn = "New syllabus introduced: Paper 1 General Studies (200 Marks) + Paper 2 Gujarati & English Language Skills (100 Marks).",
            verificationNotesGu = "નવો અભ્યાસક્રમ અમલમાં: પેપર-૧ સામાન્ય અભ્યાસ (૨૦૦ ગુણ) + પેપર-૨ ભાષા કૌશલ્ય (૧૦૦ ગુણ).",
            feeDetailsEn = "₹100 for Unreserved. Reserved & Female candidates free.",
            feeDetailsGu = "સામાન્ય વર્ગના ઉમેદવારો માટે ₹૧૦૦. અનામત તથા મહિલાઓ માટે કોઈ ફી નથી.",
            stagesEn = listOf("Physical Efficiency Test", "Physical Standard Measurement", "Main Written Examination (300 Marks)", "Document Verification"),
            stagesGu = listOf("શારીરિક દોડ કસોટી", "શારીરિક માપણી", "મુખ્ય લેખિત પરીક્ષા (૩૦૦ ગુણ)", "દસ્તાવેજ ચકાસણી"),
            requiredDocumentIds = listOf("photo", "signature", "degree_certificate", "ncl_certificate", "ews_certificate", "caste_certificate", "domicile")
        ),
        RecruitmentNotice(
            id = "gpssb-talati-mantri-2026",
            advtNo = "GPSSB/202627/10",
            board = RecruitmentBoard.GPSSB,
            titleEn = "Village Panchayat Secretary (Talati-cum-Mantri) Class 3",
            titleGu = "ગ્રામ પંચાયત સેક્રેટરી (તલાટી-કમ-મંત્રી) વર્ગ-૩",
            postCadreEn = "Panchayat Service Class 3",
            postCadreGu = "પંચાયત સેવા વર્ગ-૩",
            departmentEn = "Panchayats, Rural Housing and Rural Development Department",
            departmentGu = "પંચાયત, ગ્રામ ગૃહનિર્માણ અને ગ્રામ વિકાસ વિભાગ",
            totalVacancies = 3437,
            vacanciesBreakdownEn = "Ahmedabad: 120 | Rajkot: 185 | Surat: 190 | Vadodara: 160 | Banaskantha: 230 | Other Dists: 2,552",
            vacanciesBreakdownGu = "અમદાવાદ: ૧૨૦ | રાજકોટ: ૧૮૫ | સુરત: ૧૯૦ | વડોદરા: ૧૬૦ | બનાસકાંઠા: ૨૩૦ | અન્ય જિલ્લાઓ: ૨,૫૫૨",
            payScaleEn = "Fix pay ₹26,000 / month, then Level 2",
            payScaleGu = "ફિક્સ પગાર ₹૨૬,૦૦૦ / માસિક, ત્યારબાદ લેવલ ૨",
            applicationStartDate = "2026-10-12",
            applicationEndDate = "2026-11-18",
            isDeadlineExtended = true,
            originalEndDate = "2026-11-02",
            examDateEstimated = "March 2027",
            cutoffDateForAge = "2026-11-18",
            minAge = 18,
            maxAgeGeneral = 36,
            educationLevel = EducationLevel.HSC_12TH,
            educationDetailsEn = "12th Standard (HSC) pass from recognized board with basic computer knowledge.",
            educationDetailsGu = "માન્ય બોર્ડમાંથી ધોરણ ૧૨ પાસ અને કોમ્પ્યુટરનું પ્રાથમિક જ્ઞાન.",
            isCccRequired = true,
            hasCorrigendum = true,
            corrigendumCount = 2,
            officialPdfUrl = "https://gpssb.gujarat.gov.in/pdf/Talati_Notification_2026.pdf",
            applyPortalUrl = "https://ojas.gujarat.gov.in",
            lastVerifiedDate = "2026-10-15",
            verificationNotesEn = "District-wise vacancy roster updated as per GAD 2026 resolution. Corrigendum issued.",
            verificationNotesGu = "સામાન્ય વહીવટ વિભાગના તાજેતરના ઠરાવ અનુસાર જિલ્લાવાર રોસ્ટર સુધારીને જાહેર કરાયું.",
            feeDetailsEn = "₹100 for General. Exemption for all reserved categories.",
            feeDetailsGu = "સામાન્ય વર્ગ ₹૧૦૦. તમામ અનામત કેટેગરી અને મહિલાઓ માટે ફી માફ.",
            stagesEn = listOf("Single Stage OMR Written Examination (100 Marks)", "Document Verification & District Allotment"),
            stagesGu = listOf("એક જ તબક્કાની OMR લેખિત પરીક્ષા (૧૦૦ ગુણ)", "ડોક્યુમેન્ટ વેરિફિકેશન અને જિલ્લા ફાળવણી"),
            requiredDocumentIds = listOf("photo", "signature", "ncl_certificate", "ews_certificate", "caste_certificate", "ccc_certificate", "domicile")
        ),
        RecruitmentNotice(
            id = "gpssb-junior-clerk-2026",
            advtNo = "GPSSB/202627/12",
            board = RecruitmentBoard.GPSSB,
            titleEn = "Junior Clerk (Panchayat Administrative Service)",
            titleGu = "જુનિયર ક્લાર્ક (વહીવટ / હિસાબ) પંચાયત સેવા",
            postCadreEn = "Panchayat Service Class 3",
            postCadreGu = "પંચાયત સેવા વર્ગ-૩",
            departmentEn = "Panchayat Department, District & Taluka Panchayats",
            departmentGu = "પંચાયત વિભાગ, જિલ્લા અને તાલુકા પંચાયતો",
            totalVacancies = 2185,
            vacanciesBreakdownEn = "General Administration: 1,650 | Accounts Branch: 535",
            vacanciesBreakdownGu = "સામાન્ય વહીવટ: ૧,૬૫૦ | હિસાબી શાખા: ૫૩૫",
            payScaleEn = "Fix pay ₹26,000 / month for 5 years",
            payScaleGu = "૫ વર્ષ માટે ફિક્સ પગાર ₹૨૬,૦૦૦ / માસિક",
            applicationStartDate = "2026-09-28",
            applicationEndDate = "2026-10-30",
            isDeadlineExtended = false,
            originalEndDate = null,
            examDateEstimated = "February 2027",
            cutoffDateForAge = "2026-10-30",
            minAge = 18,
            maxAgeGeneral = 36,
            educationLevel = EducationLevel.HSC_12TH,
            educationDetailsEn = "Higher Secondary Certificate (12th Std) or equivalent.",
            educationDetailsGu = "ધોરણ ૧૨ (HSC) પાસ અથવા માન્ય સમકક્ષ લાયકાત.",
            isCccRequired = true,
            hasCorrigendum = false,
            corrigendumCount = 0,
            officialPdfUrl = "https://gpssb.gujarat.gov.in/pdf/JrClerk_2026.pdf",
            applyPortalUrl = "https://ojas.gujarat.gov.in",
            lastVerifiedDate = "2026-10-10",
            verificationNotesEn = "Verified with GPSSB Master Schedule. No amendments recorded.",
            verificationNotesGu = "GPSSB ના સત્તાવાર કેલેન્ડર મુજબ ચકાસાયેલ. કોઈ સુધારો આવેલ નથી.",
            feeDetailsEn = "₹100 for General. Exempted for reserved categories.",
            feeDetailsGu = "સામાન્ય વર્ગ ₹૧૦૦. અનામત વર્ગના ઉમેદવારો માટે ફી માફ.",
            stagesEn = listOf("Written Examination (OMR 100 Marks)", "Document Verification"),
            stagesGu = listOf("હેતુલક્ષી લેખિત પરીક્ષા (૧૦૦ ગુણ)", "દસ્તાવેજ ચકાસણી"),
            requiredDocumentIds = listOf("photo", "signature", "ncl_certificate", "ews_certificate", "caste_certificate", "ccc_certificate", "domicile")
        ),
        RecruitmentNotice(
            id = "hc-guj-dyso-2026",
            advtNo = "HCG/202627/04",
            board = RecruitmentBoard.HIGH_COURT,
            titleEn = "Deputy Section Officer (Dy. SO) High Court of Gujarat",
            titleGu = "નાયબ સેક્શન અધિકારી (Dy. SO) ગુજરાત વડી અદાલત",
            postCadreEn = "High Court Establishment Class 3",
            postCadreGu = "હાઇકોર્ટ મહેકમ વર્ગ-૩",
            departmentEn = "High Court of Gujarat, Sola, Ahmedabad",
            departmentGu = "ગુજરાત હાઇકોર્ટ, સોલા, અમદાવાદ",
            totalVacancies = 192,
            vacanciesBreakdownEn = "General: 84 | SEBC: 48 | SC: 14 | ST: 27 | EWS: 19",
            vacanciesBreakdownGu = "સામાન્ય: ૮૪ | સા.શૈ.પ.વર્ગ: ૪૮ | અનુ.જાતિ: ૧૪ | અનુ.જનજાતિ: ૨૭ | EWS: ૧૯",
            payScaleEn = "Pay Matrix Level 7 (₹39,900 - ₹1,26,600)",
            payScaleGu = "પે મેટ્રિક્સ લેવલ ૭ (₹૩૯,૯૦૦ થી ₹૧,૨૬,૬૦૦)",
            applicationStartDate = "2026-10-02",
            applicationEndDate = "2026-11-04",
            isDeadlineExtended = true,
            originalEndDate = "2026-10-22",
            examDateEstimated = "Elimination Test: 13-Dec-2026",
            cutoffDateForAge = "2026-11-04",
            minAge = 21,
            maxAgeGeneral = 35,
            educationLevel = EducationLevel.GRADUATE,
            educationDetailsEn = "Bachelor's Degree from recognized university. Working knowledge of Gujarati & English language typing.",
            educationDetailsGu = "કોઈપણ માન્ય યુનિવર્સિટીમાંથી સ્નાતક. ગુજરાતી અને અંગ્રેજી ભાષાનું સારું જ્ઞાન.",
            isCccRequired = true,
            hasCorrigendum = true,
            corrigendumCount = 1,
            officialPdfUrl = "https://gujarathighcourt.nic.in/recruitment/DySO_Detailed_2026.pdf",
            applyPortalUrl = "https://hc-ojas.gujarat.gov.in",
            lastVerifiedDate = "2026-10-15",
            verificationNotesEn = "Corrigendum published extending application date by 13 days to facilitate festive holidays.",
            verificationNotesGu = "તહેવારોની રજાઓના કારણે હાઇકોર્ટ દ્વારા અરજી કરવાની મુદત ૧૩ દિવસ લંબાવવામાં આવી.",
            feeDetailsEn = "₹1,000 for General. ₹500 for Reserved categories (SC/ST/SEBC/EWS/PwD).",
            feeDetailsGu = "સામાન્ય ઉમેદવારો માટે ₹૧,૦૦૦. અનામત કેટેગરી અને દિવ્યાંગ માટે ₹૫૦૦.",
            stagesEn = listOf("Elimination Test (Objective 100 Marks)", "Main Written Examination (100 Marks)", "Computer Typing Test (English & Gujarati)"),
            stagesGu = listOf("પ્રાથમિક એલિમિનેશન કસોટી (૧૦૦ ગુણ)", "મુખ્ય લેખિત પરીક્ષા (૧૦૦ ગુણ)", "કોમ્પ્યુટર ટાઈપિંગ કસોટી (અંગ્રેજી અને ગુજરાતી)"),
            requiredDocumentIds = listOf("photo", "signature", "degree_certificate", "ncl_certificate", "ews_certificate", "caste_certificate", "ccc_certificate")
        ),
        RecruitmentNotice(
            id = "getco-vidyut-sahayak-je-2026",
            advtNo = "GETCO/HR/2026/02",
            board = RecruitmentBoard.POWER_CORP,
            titleEn = "Vidyut Sahayak (Junior Engineer - Electrical & Civil)",
            titleGu = "વિદ્યુત સહાયક (જુનિયર એન્જિનિયર - ઇલેક્ટ્રિકલ અને સિવિલ)",
            postCadreEn = "Technical Officer Class 2 Equivalent",
            postCadreGu = "ટેકનિકલ અધિકારી વર્ગ-૨ સમકક્ષ",
            departmentEn = "Gujarat Energy Transmission Corporation Limited",
            departmentGu = "ગુજરાત એનર્જી ટ્રાન્સમિશન કોર્પોરેશન લિમિટેડ (GETCO)",
            totalVacancies = 460,
            vacanciesBreakdownEn = "Electrical: 375 | Civil: 85",
            vacanciesBreakdownGu = "ઇલેક્ટ્રિકલ: ૩૭૫ | સિવિલ: ૮૫",
            payScaleEn = "₹48,100 fixed for 1st year, then regular scale ₹45,400 - ₹1,01,200",
            payScaleGu = "પ્રથમ વર્ષ ₹૪૮,૧૦૦ ફિક્સ, ત્યારબાદ નિયમિત પગાર ₹૪૫,૪૦૦ થી ₹૧,૦૧,૨૦૦",
            applicationStartDate = "2026-09-20",
            applicationEndDate = "2026-10-28",
            isDeadlineExtended = false,
            originalEndDate = null,
            examDateEstimated = "Online CBT: 28-Nov-2026",
            cutoffDateForAge = "2026-10-28",
            minAge = 21,
            maxAgeGeneral = 35,
            educationLevel = EducationLevel.ENGINEERING,
            educationDetailsEn = "B.E. / B.Tech (Electrical / Civil) with minimum 55% marks from AICTE approved institute.",
            educationDetailsGu = "AICTE માન્ય સંસ્થામાંથી ઓછામાં ઓછા ૫૫% ગુણ સાથે B.E. / B.Tech (ઇલેક્ટ્રિકલ/સિવિલ).",
            isCccRequired = false,
            hasCorrigendum = false,
            corrigendumCount = 0,
            officialPdfUrl = "https://getco.co.in/careers/VS_JE_2026.pdf",
            applyPortalUrl = "https://www.getcogujarat.com",
            lastVerifiedDate = "2026-10-11",
            verificationNotesEn = "Verified directly from GUVNL central recruitment portal.",
            verificationNotesGu = "GUVNL પોર્ટલ પરથી ચકાસેલ. પરીક્ષા નવેમ્બરના અંતમાં ઓનલાઇન લેવાશે.",
            feeDetailsEn = "₹500 for General/SEBC/EWS. ₹250 for SC/ST.",
            feeDetailsGu = "જનરલ/SEBC/EWS માટે ₹૫૦૦. SC/ST માટે ₹૨૫૦.",
            stagesEn = listOf("Online Computer Based Test (CBT 100 Marks)", "Document Verification & Medical Fitness"),
            stagesGu = listOf("ઓનલાઇન કોમ્પ્યુટર બેઝ્ડ ટેસ્ટ (CBT ૧૦૦ ગુણ)", "દસ્તાવેજ ચકાસણી અને તબીબી તપાસ"),
            requiredDocumentIds = listOf("photo", "signature", "degree_certificate", "ncl_certificate", "ews_certificate", "caste_certificate")
        ),
        RecruitmentNotice(
            id = "gpsc-sti-state-tax-inspector-2026",
            advtNo = "GPSC/202627/08",
            board = RecruitmentBoard.GPSC,
            titleEn = "State Tax Inspector (STI) Class 3",
            titleGu = "રાજ્ય વેરા નિરીક્ષક (સ્ટેટ ટેક્સ ઇન્સ્પેક્ટર - STI) વર્ગ-૩",
            postCadreEn = "Finance Department Class 3",
            postCadreGu = "નાણાં વિભાગ વર્ગ-૩",
            departmentEn = "Chief Commissioner of State Tax, Gujarat",
            departmentGu = "રાજ્ય વેરા કમિશનર કચેરી, ગુજરાત રાજ્ય",
            totalVacancies = 520,
            vacanciesBreakdownEn = "Unreserved: 220 | SEBC: 140 | EWS: 52 | SC: 36 | ST: 72",
            vacanciesBreakdownGu = "બિનઅનામત: ૨૨૦ | સા.શૈ.પ.વર્ગ: ૧૪૦ | EWS: ૫૨ | અનુ.જાતિ: ૩૬ | અનુ.જનજાતિ: ૭૨",
            payScaleEn = "Fix pay ₹40,800 / month, then Level 7 (₹39,900 - ₹1,26,600)",
            payScaleGu = "ફિક્સ પગાર ₹૪૦,૮૦૦, ત્યારબાદ લેવલ-૭ (₹૩૯,૯૦૦ થી ₹૧,૨૬,૬૦૦)",
            applicationStartDate = "2026-10-08",
            applicationEndDate = "2026-11-10",
            isDeadlineExtended = true,
            originalEndDate = "2026-10-28",
            examDateEstimated = "Prelims: 10-Jan-2027",
            cutoffDateForAge = "2026-11-10",
            minAge = 20,
            maxAgeGeneral = 35,
            educationLevel = EducationLevel.GRADUATE,
            educationDetailsEn = "Graduate in any stream from UGC recognized university. Good comprehension of commercial concepts.",
            educationDetailsGu = "કોઈપણ શાખામાં સ્નાતકની પદવી. વેપાર અને કરવેરા વિષયક પ્રાથમિક સમજ.",
            isCccRequired = false,
            hasCorrigendum = true,
            corrigendumCount = 1,
            officialPdfUrl = "https://gpsc.gujarat.gov.in/notices/STI_Advt_08202627.pdf",
            applyPortalUrl = "https://gpsc-ojas.gujarat.gov.in",
            lastVerifiedDate = "2026-10-14",
            verificationNotesEn = "Corrigendum issued: Vacancy count increased from 460 to 520; application deadline extended to 10-Nov-2026.",
            verificationNotesGu = "સુધારા પત્રક મુજબ કુલ જગ્યા ૪૬૦ થી વધારી ૫૨૦ કરવામાં આવી અને છેલ્લી તારીખ ૧૦-નવેમ્બર સુધી લંબાવી.",
            feeDetailsEn = "₹100 for General male. Free for women & reserved categories.",
            feeDetailsGu = "સામાન્ય પુરુષો ₹૧૦૦. મહિલાઓ અને અનામત વર્ગો માટે વિનામૂલ્યે.",
            stagesEn = listOf("Preliminary Exam (200 Marks / 2 Hours)", "Main Written Examination (4 Papers - 400 Marks)", "Document Verification"),
            stagesGu = listOf("પ્રાથમિક પરીક્ષા (૨૦૦ ગુણ / ૨ કલાક)", "મુખ્ય લેખિત પરીક્ષા (૪ પેપર - ૪૦૦ ગુણ)", "અસલ પ્રમાણપત્ર ચકાસણી"),
            requiredDocumentIds = listOf("photo", "signature", "degree_certificate", "ncl_certificate", "ews_certificate", "caste_certificate", "domicile")
        ),
        RecruitmentNotice(
            id = "gsssb-sub-auditor-sub-accountant-2026",
            advtNo = "GSSSB/202627/218",
            board = RecruitmentBoard.GSSSB,
            titleEn = "Sub-Auditor / Sub-Accountant (Sachivalaya & Directorate of Accounts)",
            titleGu = "સબ-ઓડિટર / સબ-એકાઉન્ટન્ટ (સચિવાલય અને તિજોરી ખાતું)",
            postCadreEn = "Class 3 Technical Commercial",
            postCadreGu = "વર્ગ-૩ હિસાબી સંવર્ગ",
            departmentEn = "Finance Department, Gandhinagar",
            departmentGu = "નાણાં વિભાગ, ગાંધીનગર",
            totalVacancies = 689,
            vacanciesBreakdownEn = "Sub-Accountant: 440 | Sub-Auditor: 249",
            vacanciesBreakdownGu = "સબ-એકાઉન્ટન્ટ: ૪૪૦ | સબ-ઓડિટર: ૨૪૯",
            payScaleEn = "Fix pay ₹40,800 / month for 5 years, then Level 5",
            payScaleGu = "૫ વર્ષ માટે ફિક્સ પગાર ₹૪૦,૮૦૦ / માસિક, ત્યારબાદ લેવલ ૫",
            applicationStartDate = "2026-09-18",
            applicationEndDate = "2026-10-24",
            isDeadlineExtended = false,
            originalEndDate = null,
            examDateEstimated = "December 2026 (CBRT)",
            cutoffDateForAge = "2026-10-24",
            minAge = 20,
            maxAgeGeneral = 35,
            educationLevel = EducationLevel.COMMERCE_CA,
            educationDetailsEn = "Degree in Commerce (B.Com) / BBA / BCA / Mathematics / Statistics with 2nd Class.",
            educationDetailsGu = "કોમર્સ (B.Com), BBA, BCA, ગણિત અથવા આંકડાશાસ્ત્ર સાથે ઓછામાં ઓછા દ્વિતીય વર્ગમાં સ્નાતક.",
            isCccRequired = true,
            hasCorrigendum = true,
            corrigendumCount = 1,
            officialPdfUrl = "https://gsssb.gujarat.gov.in/pdf/SubAccountant_2026.pdf",
            applyPortalUrl = "https://ojas.gujarat.gov.in",
            lastVerifiedDate = "2026-10-13",
            verificationNotesEn = "Clarification notice issued regarding equivalence of specialized BBA Finance degree.",
            verificationNotesGu = "BBA ફાઇનાન્સની ડિગ્રી માન્ય ગણવા બાબતે ગૌણ સેવા દ્વારા સ્પષ્ટતા જાહેર કરવામાં આવી.",
            feeDetailsEn = "₹100 for General. Free for Reserved categories.",
            feeDetailsGu = "સામાન્ય કેટેગરી ₹૧૦૦. અનામત કેટેગરી નિઃશુલ્ક.",
            stagesEn = listOf("Part A: General Studies (60 Marks)", "Part B: Accountancy, Auditing & Taxation (90 Marks)", "Document Verification"),
            stagesGu = listOf("ભાગ-A: સામાન્ય જ્ઞાન (૬૦ ગુણ)", "ભાગ-B: નામાના મૂળતત્વો, ઓડિટિંગ અને ટેક્સેશન (૯૦ ગુણ)", "ડોક્યુમેન્ટ વેરિફિકેશન"),
            requiredDocumentIds = listOf("photo", "signature", "degree_certificate", "ncl_certificate", "ews_certificate", "caste_certificate", "ccc_certificate")
        ),
        RecruitmentNotice(
            id = "gpsc-medical-officer-class2-2026",
            advtNo = "GPSC/202627/14",
            board = RecruitmentBoard.GPSC,
            titleEn = "Medical Officer (Gujarat Public Health Service Class 2)",
            titleGu = "તબીબી અધિકારી (ગુજરાત જાહેર આરોગ્ય સેવા વર્ગ-૨)",
            postCadreEn = "Class 2 Gazetted Medical",
            postCadreGu = "વર્ગ-૨ ગેઝેટેડ તબીબી",
            departmentEn = "Health and Family Welfare Department",
            departmentGu = "આરોગ્ય અને પરિવાર કલ્યાણ વિભાગ",
            totalVacancies = 1890,
            vacanciesBreakdownEn = "PHC / CHC Centers across Gujarat",
            vacanciesBreakdownGu = "સમગ્ર ગુજરાતના પ્રાથમિક અને સામૂહિક આરોગ્ય કેન્દ્રો",
            payScaleEn = "Pay Matrix Level 9 (₹53,100 - ₹1,67,800) + NPA allowance",
            payScaleGu = "પે મેટ્રિક્સ લેવલ ૯ (₹૫૩,૧૦૦ થી ₹૧,૬૭,૮૦૦) + NPA ભથ્થું",
            applicationStartDate = "2026-10-01",
            applicationEndDate = "2026-11-12",
            isDeadlineExtended = true,
            originalEndDate = "2026-10-20",
            examDateEstimated = "Direct Interview / Primary Test in Jan 2027",
            cutoffDateForAge = "2026-11-12",
            minAge = 21,
            maxAgeGeneral = 35,
            educationLevel = EducationLevel.MEDICAL_NURSING,
            educationDetailsEn = "MBBS Degree and valid registration with Gujarat Medical Council (GMC).",
            educationDetailsGu = "MBBS ની ડિગ્રી અને ગુજરાત મેડિકલ કાઉન્સિલ (GMC) નું કાયમી રજીસ્ટ્રેશન.",
            isCccRequired = false,
            hasCorrigendum = true,
            corrigendumCount = 1,
            officialPdfUrl = "https://gpsc.gujarat.gov.in/notices/MO_Class2_Advt14.pdf",
            applyPortalUrl = "https://gpsc-ojas.gujarat.gov.in",
            lastVerifiedDate = "2026-10-15",
            verificationNotesEn = "Application window extended by GPSC to allow completion of compulsory rotatory internship.",
            verificationNotesGu = "ઇન્ટર્નશીપ પૂર્ણ કરી રહેલા ઉમેદવારો માટે GPSC દ્વારા અરજીની તારીખ ૧૨-નવેમ્બર સુધી વધારાઈ.",
            feeDetailsEn = "₹100 for General. Exempted for others.",
            feeDetailsGu = "સામાન્ય વર્ગ ₹૧૦૦. અન્ય વર્ગો માટે માફ.",
            stagesEn = listOf("Primary Elimination Test (if applications exceed ratio)", "Interview (100 Marks)", "Document Verification"),
            stagesGu = listOf("પ્રાથમિક કસોટી (જો ઉમેદવારો વધુ હોય તો)", "રૂબરૂ મુલાકાત/ઇન્ટરવ્યુ (૧૦૦ ગુણ)", "કાઉન્સિલ રજીસ્ટ્રેશન ચકાસણી"),
            requiredDocumentIds = listOf("photo", "signature", "degree_certificate", "ncl_certificate", "ews_certificate", "caste_certificate")
        ),
        RecruitmentNotice(
            id = "gpssb-gram-sevak-2026",
            advtNo = "GPSSB/202627/15",
            board = RecruitmentBoard.GPSSB,
            titleEn = "Gram Sevak (Agricultural Extension Officer Class 3)",
            titleGu = "ગ્રામ સેવક (કૃષિ વિસ્તરણ અધિકારી વર્ગ-૩)",
            postCadreEn = "Panchayat Agriculture Cadre Class 3",
            postCadreGu = "પંચાયત કૃષિ સંવર્ગ વર્ગ-૩",
            departmentEn = "Panchayats and Agriculture Department",
            departmentGu = "પંચાયત અને કૃષિ ખેડૂત કલ્યાણ વિભાગ",
            totalVacancies = 1571,
            vacanciesBreakdownEn = "District Panchayats across Gujarat State",
            vacanciesBreakdownGu = "સમગ્ર ગુજરાત રાજ્યની જિલ્લા પંચાયતો",
            payScaleEn = "Fix pay ₹26,000 / month for 5 years",
            payScaleGu = "૫ વર્ષ માટે ફિક્સ પગાર ₹૨૬,૦૦૦ / માસિક",
            applicationStartDate = "2026-10-15",
            applicationEndDate = "2026-11-28",
            isDeadlineExtended = false,
            originalEndDate = null,
            examDateEstimated = "March 2027",
            cutoffDateForAge = "2026-11-28",
            minAge = 18,
            maxAgeGeneral = 36,
            educationLevel = EducationLevel.GRADUATE,
            educationDetailsEn = "Bachelor of Science (Agriculture) / Horticulture or Diploma in Agriculture from State Agricultural University (AAU/JAU/NAU/SDAU).",
            educationDetailsGu = "માન્ય કૃષિ યુનિવર્સિટીમાંથી B.Sc. (એગ્રીકલ્ચર) / બાગાયત અથવા કૃષિ ડિપ્લોમા.",
            isCccRequired = true,
            hasCorrigendum = false,
            corrigendumCount = 0,
            officialPdfUrl = "https://gpssb.gujarat.gov.in/pdf/GramSevak_2026.pdf",
            applyPortalUrl = "https://ojas.gujarat.gov.in",
            lastVerifiedDate = "2026-10-16",
            verificationNotesEn = "Newly published notification for 2026-27 session. Online applications open on OJAS.",
            verificationNotesGu = "૨૦૨૬-૨૭ સત્ર માટે તાજી જાહેરખબર પ્રસિદ્ધ થયેલ છે. OJAS પર ઓનલાઇન ફોર્મ ભરાવવા શરૂ.",
            feeDetailsEn = "₹100 for General. Free for Reserved candidates.",
            feeDetailsGu = "સામાન્ય વર્ગ ₹૧૦૦. અનામત કેટેગરી અને મહિલાઓ માટે ફી માફ.",
            stagesEn = listOf("Written Examination (OMR 100 Marks)", "Document Verification"),
            stagesGu = listOf("હેતુલક્ષી લેખિત પરીક્ષા (૧૦૦ ગુણ)", "પ્રમાણપત્ર ચકાસણી"),
            requiredDocumentIds = listOf("photo", "signature", "degree_certificate", "ncl_certificate", "ews_certificate", "caste_certificate", "ccc_certificate", "domicile")
        )
    )

    val verifiedCorrigenda = listOf(
        CorrigendumNotice(
            id = "corr-gsssb-cce-01",
            recruitmentId = "gsssb-cce-2026",
            advtNo = "GSSSB/202627/212",
            board = RecruitmentBoard.GSSSB,
            corrigendumNo = "સુધારા પત્રક ક્રમાંક: ૨/૨૦૨૬",
            issueDate = "2026-10-14",
            changeType = CorrigendumType.DEADLINE_EXTENSION,
            headlineEn = "Application Closing Date Extended by 21 Days to 15-Nov-2026",
            headlineGu = "અરજી કરવાની છેલ્લી તારીખમાં ૨૧ દિવસનો વધારો - નવી છેલ્લી તારીખ ૧૫-નવેમ્બર-૨૦૨૬",
            summaryEn = "GSSSB extended the online registration and fee payment deadline on OJAS to accommodate technical server maintenance during peak days.",
            summaryGu = "ઉમેદવારોની સુવિધા અને સર્વર ભારણ ઘટાડવા ગૌણ સેવા મંડળ દ્વારા ઓનલાઇન અરજીની મુદત ૧૫-નવેમ્બર સુધી લંબાવી.",
            originalValueTextEn = "Application Deadline: 25-Oct-2026 (23:59 Hrs)",
            originalValueTextGu = "મૂળ છેલ્લી તારીખ: ૨૫-ઓક્ટોબર-૨૦૨૬ (રાત્રે ૧૧:૫૯ વાગ્યા સુધી)",
            revisedValueTextEn = "Revised Deadline: 15-Nov-2026 (23:59 Hrs)",
            revisedValueTextGu = "સુધારેલી નવી છેલ્લી તારીખ: ૧૫-નવેમ્બર-૨૦૨૬ (રાત્રે ૧૧:૫૯ વાગ્યા સુધી)",
            affectedSectionOrRule = "જાહેરખબર પેરા ક્રમાંક: ૪ (અરજી કરવાની પ્રક્રિયા અને મુદત)",
            officialNoticePdfUrl = "https://gsssb.gujarat.gov.in/corrigendum/CCE_Date_Ext_Notice2.pdf"
        ),
        CorrigendumNotice(
            id = "corr-police-lrb-01",
            recruitmentId = "police-lrb-constable-2026",
            advtNo = "LRB/202627/01",
            board = RecruitmentBoard.POLICE,
            corrigendumNo = "સુધારા વધારા નોટિસ ક્રમાંક: ૧/૨૦૨૬",
            issueDate = "2026-10-09",
            changeType = CorrigendumType.SYLLABUS_CHANGE,
            headlineEn = "PET Running Marks Removed from Merit; Written Test Revised to 200 Marks",
            headlineGu = "શારીરિક કસોટી (દોડ) ના ગુણ મેરિટમાંથી રદ; લેખિત પરીક્ષા હવે ૨૦૦ ગુણની રહેશે",
            summaryEn = "Home Department amended recruitment rules: Running is now strictly qualifying in nature. Merit list will be computed purely on the basis of 200 marks written exam.",
            summaryGu = "ગૃહ વિભાગના સુધારેલા નિયમ મુજબ હવે દોડ માત્ર પાસ કરવાની રહેશે (ક્વોલિફાઇંગ). આખરી પસંદગી યાદી ૨૦૦ ગુણની લેખિત કસોટી પરથી બનશે.",
            originalValueTextEn = "Physical test carried 25 marks; Combined total was 125 marks.",
            originalValueTextGu = "શારીરિક કસોટીના ૨૫ ગુણ ઉમેરાતા હતા; કુલ ૧૨૫ ગુણ પરથી પરિણામ બનતું.",
            revisedValueTextEn = "Physical test is Qualifying only. Objective Written exam is 200 Marks (3 Hours duration).",
            revisedValueTextGu = "શારીરિક કસોટી માત્ર ક્વોલિફાઇંગ રહેશે. ૨૦૦ ગુણની ઓબ્જેક્ટિવ લેખિત પરીક્ષા (૩ કલાક).",
            affectedSectionOrRule = "પોલીસ કોન્સ્ટેબલ ભરતી નિયમો - ૨૦૨૬ (નિયમ ક્રમાંક: ૮ અને ૧૨)",
            officialNoticePdfUrl = "https://lrbgujarat2026.in/pdf/LRB_Rule_Amendment_2026.pdf"
        ),
        CorrigendumNotice(
            id = "corr-gpsc-sti-01",
            recruitmentId = "gpsc-sti-state-tax-inspector-2026",
            advtNo = "GPSC/202627/08",
            board = RecruitmentBoard.GPSC,
            corrigendumNo = "સુધારા પત્રક ક્રમાંક: ૧/૨૦૨૬-૨૭",
            issueDate = "2026-10-12",
            changeType = CorrigendumType.VACANCY_REVISION,
            headlineEn = "State Tax Inspector Total Vacancies Increased from 460 to 520 (+60 Posts)",
            headlineGu = "સ્ટેટ ટેક્સ ઇન્સ્પેક્ટરની જગ્યાઓમાં વધારો: ૪૬૦ થી વધીને ૫૨૦ (+૬૦ નવી જગ્યાઓ ઉમેરાઈ)",
            summaryEn = "Finance Department sanctioned 60 additional positions across Ahmedabad, Surat, and Rajkot corporate divisions, increasing SEBC vacancies by 22 and EWS by 8.",
            summaryGu = "નાણાં વિભાગ દ્વારા વધુ ૬૦ જગ્યાઓ મંજૂર કરાતા સા.શૈ.પ.વર્ગમાં ૨૨ અને EWS માં ૮ જગ્યાઓ વધી છે.",
            originalValueTextEn = "Total Vacancies: 460 Posts",
            originalValueTextGu = "કુલ જગ્યા: ૪૬૦ જગ્યાઓ",
            revisedValueTextEn = "Total Vacancies: 520 Posts (Unreserved 220, SEBC 140, EWS 52, SC 36, ST 72)",
            revisedValueTextGu = "કુલ જગ્યા: ૫૨૦ જગ્યાઓ (બિનઅનામત ૨૨૦, સા.શૈ.પ.વર્ગ ૧૪૦, EWS ૫૨, SC ૩૬, ST ૭૨)",
            affectedSectionOrRule = "જાહેરખબર પરિશિષ્ટ-૧ (કેટેગરીવાર જગ્યા વિગત)",
            officialNoticePdfUrl = "https://gpsc.gujarat.gov.in/notices/STI_Corrigendum1_Vacancies.pdf"
        ),
        CorrigendumNotice(
            id = "corr-hc-dyso-01",
            recruitmentId = "hc-guj-dyso-2026",
            advtNo = "HCG/202627/04",
            board = RecruitmentBoard.HIGH_COURT,
            corrigendumNo = "HC Corrigendum Notification No. RC/1434/2026",
            issueDate = "2026-10-15",
            changeType = CorrigendumType.DEADLINE_EXTENSION,
            headlineEn = "High Court Dy. SO Online Registration Extended to 04-Nov-2026",
            headlineGu = "હાઇકોર્ટ Dy. SO ની ઓનલાઇન રજીસ્ટ્રેશન મુદત લંબાવી ૦૪-નવેમ્બર-૨૦૨૬ કરવામાં આવી",
            summaryEn = "Honourable High Court committee approved extension of online application and fee submission due to court Diwali recess.",
            summaryGu = "હાઇકોર્ટ ભરતી સમિતિ દ્વારા દિવાળી વેકેશન ધ્યાને રાખી ઉમેદવારો માટે ફોર્મ ભરવાની મુદત લંબાવવામાં આવી.",
            originalValueTextEn = "Closing Date: 22-Oct-2026",
            originalValueTextGu = "અગાઉની છેલ્લી તારીખ: ૨૨-ઓક્ટોબર-૨૦૨૬",
            revisedValueTextEn = "Revised Closing Date: 04-Nov-2026",
            revisedValueTextGu = "નવી છેલ્લી તારીખ: ૦૪-નવેમ્બર-૨૦૨૬",
            affectedSectionOrRule = "Clause 3: Schedule of Online Application & Fee Payment",
            officialNoticePdfUrl = "https://gujarathighcourt.nic.in/recruitment/DySO_Extension_Notice.pdf"
        ),
        CorrigendumNotice(
            id = "corr-gpssb-talati-01",
            recruitmentId = "gpssb-talati-mantri-2026",
            advtNo = "GPSSB/202627/10",
            board = RecruitmentBoard.GPSSB,
            corrigendumNo = "સુધારા પત્રક ક્રમાંક: ૧/૨૦૨૬",
            issueDate = "2026-10-11",
            changeType = CorrigendumType.DEADLINE_EXTENSION,
            headlineEn = "Talati-cum-Mantri Application Extended to 18-Nov-2026",
            headlineGu = "તલાટી-કમ-મંત્રી ભરતીમાં અરજી કરવાની મુદત ૧૮-નવેમ્બર સુધી લંબાવવામાં આવી",
            summaryEn = "Panchayat board revised schedule following changes in reservation roster for Banaskantha and Kutch border talukas.",
            summaryGu = "સરહદી તાલુકાઓમાં અનામત રોસ્ટર અપડેટ થવાના કારણે પંચાયત મંડળે છેલ્લી તારીખમાં વધારો કર્યો.",
            originalValueTextEn = "Closing Date: 02-Nov-2026",
            originalValueTextGu = "મૂળ છેલ્લી તારીખ: ૦૨-નવેમ્બર-૨૦૨૬",
            revisedValueTextEn = "Revised Closing Date: 18-Nov-2026",
            revisedValueTextGu = "સુધારેલ છેલ્લી તારીખ: ૧૮-નવેમ્બર-૨૦૨૬",
            affectedSectionOrRule = "પેરા ક્રમાંક: ૨ (અરજી કરવાની મુદત)",
            officialNoticePdfUrl = "https://gpssb.gujarat.gov.in/pdf/Talati_Date_Ext_Corr1.pdf"
        )
    )

    // Gujarat Civil Services Age & Eligibility Calculator Engine
    fun calculateEligibility(
        notice: RecruitmentNotice,
        profile: CandidateProfile
    ): EligibilityVerdict {
        // Calculate age as of cutoff date (YYYY-MM-DD)
        val cutoffParts = notice.cutoffDateForAge.split("-")
        val cutoffYear = cutoffParts[0].toIntOrNull() ?: 2026
        val cutoffMonth = cutoffParts.getOrNull(1)?.toIntOrNull() ?: 11
        val cutoffDay = cutoffParts.getOrNull(2)?.toIntOrNull() ?: 15

        var years = cutoffYear - profile.birthYear
        var months = cutoffMonth - profile.birthMonth
        var days = cutoffDay - profile.birthDay

        if (days < 0) {
            months -= 1
            days += 30
        }
        if (months < 0) {
            years -= 1
            months += 12
        }

        // Calculate permissible age relaxation under Gujarat Govt Rules
        var totalRelaxationYears = 0
        val relaxationsEn = mutableListOf<String>()
        val relaxationsGu = mutableListOf<String>()

        if (!profile.isGujaratDomicile && profile.category != CandidateCategory.GENERAL) {
            relaxationsEn.add("Non-Gujarat Domicile: Treated as Unreserved/General Category")
            relaxationsGu.add("ગુજરાતના વતની ન હોવાથી સામાન્ય (બિનઅનામત) કેટેગરી તરીકે ગણાશે")
        } else {
            // Gujarat Domicile category relaxations
            when (profile.category) {
                CandidateCategory.GENERAL -> {
                    if (profile.gender == Gender.FEMALE) {
                        totalRelaxationYears += 5
                        relaxationsEn.add("General Category Female: +5 Years")
                        relaxationsGu.add("સામાન્ય વર્ગની મહિલા ઉમેદવાર: +૫ વર્ષ છૂટછાટ")
                    }
                }
                CandidateCategory.EWS, CandidateCategory.SEBC, CandidateCategory.SC, CandidateCategory.ST -> {
                    if (profile.gender == Gender.MALE) {
                        totalRelaxationYears += 5
                        relaxationsEn.add("${profile.category.code} Male: +5 Years")
                        relaxationsGu.add("${profile.category.labelGu} પુરુષ: +૫ વર્ષ છૂટછાટ")
                    } else {
                        totalRelaxationYears += 10
                        relaxationsEn.add("${profile.category.code} Female: +10 Years (+5 reserved + 5 woman)")
                        relaxationsGu.add("${profile.category.labelGu} મહિલા: +૧૦ વર્ષ છૂટછાટ (+૫ અનામત + ૫ મહિલા)")
                    }
                }
            }
        }

        if (profile.isDifferentlyAbled) {
            totalRelaxationYears += 10
            relaxationsEn.add("Differently Abled (PwD): +10 Years")
            relaxationsGu.add("દિવ્યાંગ ઉમેદવાર (PwD): +૧૦ વર્ષ છૂટછાટ")
        }

        if (profile.isExServiceman && profile.exServiceYears > 0) {
            val exRelax = profile.exServiceYears + 3
            totalRelaxationYears += exRelax
            relaxationsEn.add("Ex-Servicemen: Service (${profile.exServiceYears} yrs) + 3 Years = +$exRelax Years")
            relaxationsGu.add("માજી સૈનિક: નોકરીનો સમય (${profile.exServiceYears} વર્ષ) + ૩ વર્ષ = +$exRelax વર્ષ છૂટછાટ")
        }

        val maxPermissibleAge = notice.maxAgeGeneral + totalRelaxationYears

        // Check age bounds
        if (years < notice.minAge) {
            return EligibilityVerdict(
                status = EligibilityStatus.AGE_UNDER_LIMIT,
                calculatedAgeYears = years,
                calculatedAgeMonths = months,
                calculatedAgeDays = days,
                permissibleMaxAge = maxPermissibleAge,
                baseMaxAge = notice.maxAgeGeneral,
                totalRelaxationYears = totalRelaxationYears,
                statusTitleEn = "Under Minimum Age Limit",
                statusTitleGu = "લઘુત્તમ વયમર્યાદા કરતાં નાની ઉંમર",
                explanationEn = "Candidate will be $years years, $months months on the cutoff date ($notice.cutoffDateForAge). Minimum required age is ${notice.minAge} years.",
                explanationGu = "કટ-ઓફ તારીખે ઉમેદવારની ઉંમર $years વર્ષ, $months મહિના થાય છે. લઘુત્તમ વયમર્યાદા ${notice.minAge} વર્ષ છે.",
                appliedRelaxationsEn = relaxationsEn,
                appliedRelaxationsGu = relaxationsGu
            )
        }

        if (years > maxPermissibleAge) {
            return EligibilityVerdict(
                status = EligibilityStatus.AGE_OVER_LIMIT,
                calculatedAgeYears = years,
                calculatedAgeMonths = months,
                calculatedAgeDays = days,
                permissibleMaxAge = maxPermissibleAge,
                baseMaxAge = notice.maxAgeGeneral,
                totalRelaxationYears = totalRelaxationYears,
                statusTitleEn = "Over Maximum Age Limit",
                statusTitleGu = "મહત્તમ વયમર્યાદા બહાર (ઓવરેજ)",
                explanationEn = "Candidate is $years years on cutoff date. Maximum permissible age after relaxations is $maxPermissibleAge years (Base: ${notice.maxAgeGeneral} + Relaxation: $totalRelaxationYears).",
                explanationGu = "કટ-ઓફ તારીખે ઉમેદવારની ઉંમર $years વર્ષ થાય છે. છૂટછાટ સાથે મહત્તમ માન્ય વયમર્યાદા $maxPermissibleAge વર્ષ છે (મૂળ: ${notice.maxAgeGeneral} + છૂટછાટ: $totalRelaxationYears).",
                appliedRelaxationsEn = relaxationsEn,
                appliedRelaxationsGu = relaxationsGu
            )
        }

        // Check Education qualification match
        val educationMatches = when (notice.educationLevel) {
            EducationLevel.ANY -> true
            EducationLevel.SSC_10TH -> true
            EducationLevel.HSC_12TH -> profile.educationLevel != EducationLevel.SSC_10TH
            EducationLevel.GRADUATE -> profile.educationLevel in listOf(
                EducationLevel.GRADUATE,
                EducationLevel.ENGINEERING,
                EducationLevel.COMMERCE_CA,
                EducationLevel.MEDICAL_NURSING,
                EducationLevel.LAW
            )
            EducationLevel.ENGINEERING -> profile.educationLevel == EducationLevel.ENGINEERING
            EducationLevel.COMMERCE_CA -> profile.educationLevel == EducationLevel.COMMERCE_CA
            EducationLevel.MEDICAL_NURSING -> profile.educationLevel == EducationLevel.MEDICAL_NURSING
            EducationLevel.LAW -> profile.educationLevel == EducationLevel.LAW
        }

        if (!educationMatches) {
            return EligibilityVerdict(
                status = EligibilityStatus.EDUCATION_MISMATCH,
                calculatedAgeYears = years,
                calculatedAgeMonths = months,
                calculatedAgeDays = days,
                permissibleMaxAge = maxPermissibleAge,
                baseMaxAge = notice.maxAgeGeneral,
                totalRelaxationYears = totalRelaxationYears,
                statusTitleEn = "Education Qualification Mismatch",
                statusTitleGu = "શૈક્ષણિક લાયકાત મેળ ખાતી નથી",
                explanationEn = "This post specifically requires '${notice.educationDetailsEn}'. Your selected qualification is '${profile.educationLevel.labelEn}'.",
                explanationGu = "આ જગ્યા માટે '${notice.educationDetailsGu}' જરૂરી છે. તમારી લાયકાત '${profile.educationLevel.labelGu}' દર્શાવેલ છે.",
                appliedRelaxationsEn = relaxationsEn,
                appliedRelaxationsGu = relaxationsGu
            )
        }

        return EligibilityVerdict(
            status = EligibilityStatus.ELIGIBLE,
            calculatedAgeYears = years,
            calculatedAgeMonths = months,
            calculatedAgeDays = days,
            permissibleMaxAge = maxPermissibleAge,
            baseMaxAge = notice.maxAgeGeneral,
            totalRelaxationYears = totalRelaxationYears,
            statusTitleEn = "Eligible to Apply (પાત્ર)",
            statusTitleGu = "અરજી કરવા માટે પૂર્ણ પાત્ર (Eligible)",
            explanationEn = "Age ($years yrs, $months mos) is within permissible limit of $maxPermissibleAge years. Educational criteria satisfied.",
            explanationGu = "કટ-ઓફ તારીખે વયમર્યાદા ($years વર્ષ, $months માસ) માન્ય મર્યાદા ($maxPermissibleAge વર્ષ) ની અંદર છે અને શૈક્ષણિક લાયકાત મેળ ખાય છે.",
            appliedRelaxationsEn = relaxationsEn,
            appliedRelaxationsGu = relaxationsGu
        )
    }
}
