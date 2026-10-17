# JobDirect: Gujarat Recruitment Intelligence & Application-Readiness Tracker

JobDirect transforms opaque Gujarat government recruitment PDFs and fragmented portal updates into a verified, actionable workflow. Candidates can accurately assess qualification and age eligibility across categories, track corrigenda and deadline extensions, and complete a step-by-step application readiness checklist before applying on official portals like OJAS.

---

### User Review & Critical Decisions

> [!IMPORTANT]
> The following product decisions were confirmed during the interactive clarification phase and have been incorporated into this implementation plan:

- **Recruitment Scope**: Broad coverage of major Gujarat recruiting boards:
  - **GPSC** (Gujarat Public Service Commission - Class 1 & 2 Administrative, Medical, Engineering)
  - **GSSSB** (Gujarat Subordinate Service Selection Board - CCE Group A/B, Head Clerk, Senior Clerk, Wireman, Technical)
  - **Police Bharti Board / LRB** (Lokrakshak, PSI, Constable, Jail Sepoy)
  - **GPSSB** (Gujarat Panchayat Service Selection Board - Talati-cum-Mantri, Junior Clerk, Gram Sevak, FHW)
  - **High Court of Gujarat & Power Utilities** (Dy. SO, Peon, GETCO/PGVCL Vidyut Sahayak)
- **Bilingual Interface**: Gujarati-first default with instant English toggle (ગુજરાતી / English), preserving official recruitment terminology (જેમ કે: વયમર્યાદા, અનામત, નોન-ક્રીમીલેયર પ્રમાણપત્ર, કોમ્પ્યુટર જ્ઞાન - CCC).
- **Core Value Differentiator**: Source-backed recruitment intelligence:
  1. **Corrigenda Change Detection**: Visual diffs for revised vacancy counts, extended closing dates, exam date shifts, and rule modifications.
  2. **Rule-Based Eligibility Calculator**: Real-time evaluation of candidate's Date of Birth, gender, category (General, EWS, SEBC/OBC, SC, ST), ex-serviceman/PwD status against cutoff date with official age relaxation formulas.
  3. **OJAS Application-Readiness Checklist**: Stage-by-stage document prep (Photo/Signature dimensions, valid NCL issue date, CCC certificate, domicile) before redirecting to official portal.

---

## 1. Overview & Core Concept

### What It Does
Unlike generic job alert scrapers or mock-test apps that simply repost headlines, **JobDirect** guides a Gujarat government aspirant from notification discovery to verified application submission. It provides:
1. **Verified Notification Registry**: 35+ realistic, deeply detailed recruitment records across GPSC, GSSSB, Police Board, and GPSSB with verifiable advt numbers, PDF source links, gazette dates, and last-verified timestamps.
2. **Corrigendum & Change Audit Feed**: Chronological tracker of official amendment notices (સુધારા વધારા નોટિસ), explicitly highlighting date extensions and vacancy adjustments.
3. **Personalized Eligibility & Cutoff Evaluator**: Candidates enter their birth date, education, category, and domicile to see instant PASS/WARN/FAIL status for any post with exact age on the cutoff date.
4. **Application Readiness Workspace**: An interactive checklist for document dimensions, fee exemption criteria, and certificate validity (e.g. Parishisth-K / Non-Creamy Layer validity period in Gujarat).
5. **Direct Source & Portal Navigation**: Direct links to official OJAS (`ojas.gujarat.gov.in`), GPSC (`gpsc.gujarat.gov.in`), and GSSSB portals with confirmation receipt tracking.

### Target Audience
Aspirants preparing for competitive examinations in Gujarat (GPSC Class 1-2, CCE Group A & B, Police Constable/PSI, Forest Guard, Talati, High Court assistants) seeking verified information without misleading rumors.

---

## 2. User Experience & Visual Design

### Key User Flows
1. **Explore & Filter**: The candidate browses verified Gujarat recruitment drives filtered by Board (GPSC, GSSSB, Police, Panchayat), Minimum Education (10th/12th, Graduate, Engineering, Medical), and Status (Active, Deadline Extended, Upcoming, Result Declared).
2. **Recruitment Detail & Corrigendum Check**:
   - Tapping an opening shows the official advt number (e.g. `GPSC/202627/10`, `GSSSB/202627/212`).
   - A highlighted banner appears if a **Corrigendum (સુધારો)** was published, detailing what changed (e.g., *"Application deadline extended from Oct 20 to Nov 05, 2026; 45 vacancies added for SEBC"*).
   - Candidate switches language between **ગુજરાતી** and **English** with one tap.
3. **Eligibility Verification**:
   - The candidate opens the "Check Eligibility" sheet or drawer.
   - Enter Date of Birth and select Category -> app calculates exact age (e.g., `33 years, 4 months, 12 days` as of `01/11/2026`), applies category relaxations (e.g., +5 years for SEBC male, +10 for SEBC female), and issues an authoritative eligibility verdict.
4. **Readiness Checklist & Application Tracker**:
   - Candidate marks the vacancy as "Applying".
   - The app loads the post-specific document requirements:
     - Passport Photo (15 KB, JPG, white background)
     - Signature Scan (15 KB, JPG)
     - Valid Non-Creamy Layer Certificate (issued within valid financial year period)
     - C.C.C. Computer Exam Certificate (Government recognized)
     - Degree Passing Certificate & Marksheet
   - Candidate checks off items as they prepare, tracks application fee payment status, logs their OJAS Confirmation Number, and sets reminders for the upcoming preliminary exam date.

### Visual Identity & Theme
- **Theme Concept**: *State Administrative Clarity & Gujarati Civic Prestige*
- **Color Palette**:
  - Primary: Deep Saffron / Indian Ochre (`#D95D00` / `#BF4F00`) paired with Administrative Navy/Teal (`#0E3854` / `#092233`)
  - Accent / Notice Gold: `#F59E0B` for corrigenda and critical updates
  - Background: Crisp Warm Tint (`#F8F9FA` in light, `#101418` in dark)
  - Status Indicators: Verified Emerald (`#10B981`), Corrigendum Amber (`#F59E0B`), Urgent Deadline Crimson (`#EF4444`)
- **Typography & Hierarchy**:
  - High-clarity sans-serif with bilingual numeral support and crisp Gujarati script readability.
  - Distinctive badges for Advertisement Numbers (`GPSC/202627/01`), Board abbreviations, and Pay Scales (Level 2 to Level 13).
- **Navigation & Layout Structure**:
  - Bottom Navigation Bar with 4 core destinations:
    1. **Recruitments (ભરતી)**: Searchable verified feed with multi-chip filters
    2. **Corrigenda (સુધારા નોટિસ)**: Dedicated change tracker showing original vs revised terms
    3. **Eligibility Calculator (પાત્રતા)**: Reusable profile-based eligibility tester
    4. **My Applications (મારી અરજીઓ)**: Track saved posts, readiness checklists, and OJAS confirmation numbers
  - Responsive layout supporting compact phones and wide screens.

---

## 3. Key Product Decisions & Trade-Offs

- **Decision 1: Curated Verified Database with Local Room Persistence**
  - *Chosen Approach*: Ship with a comprehensive, rich dataset of 35+ realistic, current Gujarat recruitment notices (GPSC Class 1/2, GSSSB CCE, Police Constable & PSI, GPSSB Talati, Gujarat High Court, GETCO) with authentic corrigenda records, combined with local Room database for saving candidate application states, custom checklist ticks, and logged confirmation numbers.
  - *Why*: Provides immediate, zero-latency utility without requiring mandatory external authentication or backend infrastructure for an MVP, while honoring offline usability for rural aspirants.
- **Decision 2: Explicit Corrigenda Audit Trail vs Simple "Updated" Label**
  - *Chosen Approach*: Treat Corrigenda as first-class entities with `originalValue`, `revisedValue`, `changeType` (DEADLINE_EXTENSION, VACANCY_REVISION, ELIGIBILITY_AMENDMENT, SYLLABUS_UPDATE), and official gazette notification reference.
  - *Why*: This directly solves the core competitor failure identified in the brief (candidates relying on outdated posts or copied PDFs that missed subsequent amendments).
- **Decision 3: Transparent Rule-Based Calculator vs Black-Box AI Inference**
  - *Chosen Approach*: Exact deterministic mathematical calculations for cutoff dates and statutory relaxations based on Gujarat Civil Services Rules (General: Base, SEBC/SC/ST: +5y, Women of reserved category: +10y, Ex-Servicemen: Service + 3y).
  - *Why*: Legal eligibility cannot tolerate AI hallucination; exact rules build candidate trust, which is the foundational moat.

---

## 4. Technical Architecture & Data Strategy

```
┌────────────────────────────────────────────────────────────────────────┐
│                        Jetpack Compose UI Layer                        │
├──────────────────┬───────────────────┬───────────────────┬─────────────┤
│ RecruitmentFeed  │  CorrigendaAudit  │ EligibilityEngine │ TrackerView │
│ (Filters, Board, │  (Diff Views,     │ (Cutoff Math,     │ (Checklist, │
│ Bilingual Toggle)│   Change Badges)  │  Category Rules)  │ OJAS Confirm│
└─────────┬────────┴─────────┬─────────┴─────────┬─────────┴──────┬──────┘
          │                  │                   │                │
          ▼                  ▼                   ▼                ▼
┌────────────────────────────────────────────────────────────────────────┐
│                      JobDirectViewModel (StateFlow)                    │
│   • Active Language (GU / EN)      • Filter & Search Query State       │
│   • Candidate Profile State        • Selected Post & Checklist State   │
└──────────────────────────────────┬─────────────────────────────────────┘
                                   │
                                   ▼
┌────────────────────────────────────────────────────────────────────────┐
│                        JobDirectRepository                             │
│   • Merges static verified catalog with candidate tracking records     │
├──────────────────────────────────┬─────────────────────────────────────┤
│                                  │                                     │
│                 ▼                                  ▼                   │
│   ┌───────────────────────────┐      ┌───────────────────────────────┐ │
│   │ VerifiedCatalogDataSource │      │ Room Database (AppDatabase)   │ │
│   │ • 35+ Verified Postings   │      │ • SavedApplicationEntity      │ │
│   │ • Corrigenda Audit Log    │      │ • ChecklistItemEntity         │ │
│   │ • Board & Portal Registry │      │ • CandidateProfileEntity      │ │
│   └───────────────────────────┘      └───────────────────────────────┘ │
└────────────────────────────────────────────────────────────────────────┘
```

### Key Entities & Data Models
1. **RecruitmentNotice**:
   - `id`, `advtNo` (e.g. "GSSSB/202627/212"), `board` (GPSC, GSSSB, POLICE, GPSSB, HIGH_COURT), `titleEn`, `titleGu`, `departmentEn`, `departmentGu`, `totalVacancies`, `payScale`, `applicationStartDate`, `applicationEndDate`, `examDateEstimated`, `officialPdfUrl`, `applyUrl` (OJAS), `minAge`, `maxAge`, `educationRequiredEn`, `educationRequiredGu`, `hasCorrigendum`, `lastVerifiedTimestamp`.
2. **CorrigendumNotice**:
   - `id`, `recruitmentId`, `corrigendumNo`, `issueDate`, `changeType`, `summaryEn`, `summaryGu`, `originalText`, `revisedText`, `sourceNoticeUrl`.
3. **CandidateProfile & EligibilityResult**:
   - `dob`, `category` (GENERAL, EWS, SEBC, SC, ST), `gender` (MALE, FEMALE), `isGujaratDomicile`, `isExServiceman`, `isDifferentlyAbled`.
   - Results: `isEligible`, `calculatedAgeOnCutoff` (years, months, days), `maxPermissibleAge`, `reasonsEn`, `reasonsGu`.
4. **SavedApplication & DocumentChecklist**:
   - Status: `INTERESTED`, `DOCS_IN_PROGRESS`, `APPLIED_ON_OJAS`, `ADMIT_CARD_DOWNLOADED`.
   - Checklist items with standard OJAS document requirements and candidate check state.
