// ��� MFC ʾ��Դ������ʾ���ʹ�� MFC Microsoft Office Fluent �û����� 
// (��Fluent UI��)����ʾ�������ο���
// ���Բ��䡶Microsoft ������ο����� 
// MFC C++ ������渽����ص����ĵ���  
// ���ơ�ʹ�û�ַ� Fluent UI ����������ǵ����ṩ�ġ�  
// ��Ҫ�˽��й� Fluent UI ��ɼƻ�����ϸ��Ϣ�������  
// http://go.microsoft.com/fwlink/?LinkId=238214��
//
// ��Ȩ����(C) Microsoft Corporation
// ��������Ȩ����

// MainFrm.cpp : CMainFrame ���ʵ��
//

#include "stdafx.h"
#include "MFCApplication1.h"

#include "MainFrm.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif

extern void setGlobalHook(HWND hwnd);

void HookNcActivateMessage(HWND hwnd);
void UnhookNcActivateMessage(HWND hwnd);
LRESULT   CALLBACK   HookProc1(int   nCode, WPARAM   wParam, LPARAM   lParam);
LRESULT   CALLBACK   HookProc2(int   nCode, WPARAM   wParam, LPARAM   lParam);
LRESULT   CALLBACK   HookProc3(int   nCode, WPARAM   wParam, LPARAM   lParam);
LRESULT   CALLBACK   HookProc4(int   nCode, WPARAM   wParam, LPARAM   lParam);

// CMainFrame

IMPLEMENT_DYNAMIC(CMainFrame, CFrameWndEx)

BEGIN_MESSAGE_MAP(CMainFrame, CFrameWndEx)
	ON_WM_CREATE()
	ON_WM_DESTROY()
	ON_WM_SETFOCUS()
	ON_WM_TIMER()
	ON_COMMAND_RANGE(ID_VIEW_APPLOOK_WIN_2000, ID_VIEW_APPLOOK_WINDOWS_7, &CMainFrame::OnApplicationLook)
	ON_UPDATE_COMMAND_UI_RANGE(ID_VIEW_APPLOOK_WIN_2000, ID_VIEW_APPLOOK_WINDOWS_7, &CMainFrame::OnUpdateApplicationLook)
	ON_MESSAGE(WM_NCACTIVATE, CMainFrame::MessageHandler)
END_MESSAGE_MAP()

// CMainFrame ����/����

CMainFrame::CMainFrame()
{
	// TODO:  �ڴ���ӳ�Ա��ʼ������
	theApp.m_nAppLook = theApp.GetInt(_T("ApplicationLook"), ID_VIEW_APPLOOK_VS_2008);
}

CMainFrame::~CMainFrame()
{
}

int CMainFrame::OnCreate(LPCREATESTRUCT lpCreateStruct)
{
	if (CFrameWndEx::OnCreate(lpCreateStruct) == -1)
		return -1;

	// BOOL bNameValid;

	// ����һ����ͼ��ռ�ÿ�ܵĹ�����
	if (!m_wndView.Create(NULL, NULL, AFX_WS_DEFAULT_VIEW, CRect(0, 0, 0, 0), this, AFX_IDW_PANE_FIRST, NULL))
	{
		TRACE0("δ�ܴ�����ͼ����\n");
		return -1;
	}

	//m_wndRibbonBar.Create(this);
	//m_wndRibbonBar.LoadFromResource(IDR_RIBBON);

	// ���� Visual Studio 2005 ��ʽͣ��������Ϊ
	//CDockingManager::SetDockingMode(DT_SMART);
	// ���� Visual Studio 2005 ��ʽͣ�������Զ�������Ϊ
	//EnableAutoHidePanes(CBRS_ALIGN_ANY);
	// ���ڳ־�ֵ�����Ӿ�����������ʽ
	OnApplicationLook(theApp.m_nAppLook);
	HookNcActivateMessage(m_hWnd);
	setGlobalHook(m_hWnd);
	SetTimer(0, 1, NULL);

	return 0;
}

void CMainFrame::OnDestroy()
{
	UnhookNcActivateMessage(m_hWnd);
	CFrameWndEx::OnDestroy();
}

BOOL CMainFrame::PreCreateWindow(CREATESTRUCT& cs)
{
	if( !CFrameWndEx::PreCreateWindow(cs) )
		return FALSE;
	// TODO:  �ڴ˴�ͨ���޸�
	//  CREATESTRUCT cs ���޸Ĵ��������ʽ
	/*
		WS_CAPTION | WS_MAXIMIZEBOX | WS_MINIMIZEBOX | FWS_ADDTOTITLE | 
	*/
	cs.style = WS_OVERLAPPED | WS_THICKFRAME | WS_SYSMENU;

	cs.dwExStyle &= ~WS_EX_CLIENTEDGE;
	cs.dwExStyle |= WS_EX_TOOLWINDOW; // WS_EX_NOACTIVATE | WS_EX_TOPMOST;
	cs.lpszClass = AfxRegisterWndClass(0);
	return TRUE;
}

// CMainFrame ���

#ifdef _DEBUG
void CMainFrame::AssertValid() const
{
	CFrameWndEx::AssertValid();
}

void CMainFrame::Dump(CDumpContext& dc) const
{
	CFrameWndEx::Dump(dc);
}
#endif //_DEBUG


// CMainFrame ��Ϣ�������

void CMainFrame::OnSetFocus(CWnd* /*pOldWnd*/)
{
	// ������ǰ�Ƶ���ͼ����
	m_wndView.SetFocus();
}

BOOL CMainFrame::OnCmdMsg(UINT nID, int nCode, void* pExtra, AFX_CMDHANDLERINFO* pHandlerInfo)
{
	// ����ͼ��һ�γ��Ը�����
	if (m_wndView.OnCmdMsg(nID, nCode, pExtra, pHandlerInfo))
		return TRUE;

	// ����ִ��Ĭ�ϴ���
	return CFrameWndEx::OnCmdMsg(nID, nCode, pExtra, pHandlerInfo);
}

void CMainFrame::OnApplicationLook(UINT id)
{
	CWaitCursor wait;

	theApp.m_nAppLook = id;

	switch (theApp.m_nAppLook)
	{
	case ID_VIEW_APPLOOK_WIN_2000:
		CMFCVisualManager::SetDefaultManager(RUNTIME_CLASS(CMFCVisualManager));
		m_wndRibbonBar.SetWindows7Look(FALSE);
		break;

	case ID_VIEW_APPLOOK_OFF_XP:
		CMFCVisualManager::SetDefaultManager(RUNTIME_CLASS(CMFCVisualManagerOfficeXP));
		m_wndRibbonBar.SetWindows7Look(FALSE);
		break;

	case ID_VIEW_APPLOOK_WIN_XP:
		CMFCVisualManagerWindows::m_b3DTabsXPTheme = TRUE;
		CMFCVisualManager::SetDefaultManager(RUNTIME_CLASS(CMFCVisualManagerWindows));
		m_wndRibbonBar.SetWindows7Look(FALSE);
		break;

	case ID_VIEW_APPLOOK_OFF_2003:
		CMFCVisualManager::SetDefaultManager(RUNTIME_CLASS(CMFCVisualManagerOffice2003));
		CDockingManager::SetDockingMode(DT_SMART);
		m_wndRibbonBar.SetWindows7Look(FALSE);
		break;

	case ID_VIEW_APPLOOK_VS_2005:
		CMFCVisualManager::SetDefaultManager(RUNTIME_CLASS(CMFCVisualManagerVS2005));
		CDockingManager::SetDockingMode(DT_SMART);
		m_wndRibbonBar.SetWindows7Look(FALSE);
		break;

	case ID_VIEW_APPLOOK_VS_2008:
		CMFCVisualManager::SetDefaultManager(RUNTIME_CLASS(CMFCVisualManagerVS2008));
		CDockingManager::SetDockingMode(DT_SMART);
		m_wndRibbonBar.SetWindows7Look(FALSE);
		break;

	case ID_VIEW_APPLOOK_WINDOWS_7:
		CMFCVisualManager::SetDefaultManager(RUNTIME_CLASS(CMFCVisualManagerWindows7));
		CDockingManager::SetDockingMode(DT_SMART);
		m_wndRibbonBar.SetWindows7Look(TRUE);
		break;

	default:
		switch (theApp.m_nAppLook)
		{
		case ID_VIEW_APPLOOK_OFF_2007_BLUE:
			CMFCVisualManagerOffice2007::SetStyle(CMFCVisualManagerOffice2007::Office2007_LunaBlue);
			break;

		case ID_VIEW_APPLOOK_OFF_2007_BLACK:
			CMFCVisualManagerOffice2007::SetStyle(CMFCVisualManagerOffice2007::Office2007_ObsidianBlack);
			break;

		case ID_VIEW_APPLOOK_OFF_2007_SILVER:
			CMFCVisualManagerOffice2007::SetStyle(CMFCVisualManagerOffice2007::Office2007_Silver);
			break;

		case ID_VIEW_APPLOOK_OFF_2007_AQUA:
			CMFCVisualManagerOffice2007::SetStyle(CMFCVisualManagerOffice2007::Office2007_Aqua);
			break;
		}

		CMFCVisualManager::SetDefaultManager(RUNTIME_CLASS(CMFCVisualManagerOffice2007));
		CDockingManager::SetDockingMode(DT_SMART);
		m_wndRibbonBar.SetWindows7Look(FALSE);
	}

	RedrawWindow(NULL, NULL, RDW_ALLCHILDREN | RDW_INVALIDATE | RDW_UPDATENOW | RDW_FRAME | RDW_ERASE);

	theApp.WriteInt(_T("ApplicationLook"), theApp.m_nAppLook);
}

void CMainFrame::OnUpdateApplicationLook(CCmdUI* pCmdUI)
{
	pCmdUI->SetRadio(theApp.m_nAppLook == pCmdUI->m_nID);
}

extern LPWSTR message;
extern LPWSTR message2;
extern LPWSTR message3;
CString s;
CString s2;
CString s3;
int i = 0;
void logMessage(LPWSTR log, UINT msg, WPARAM wParam, LPARAM lParam)
{
	//theApp.WriteString(_T("ApplicationLook"), log);
	s.Format(L"%s: I %d M %d W %d L %d D %d", log, i, msg, wParam, lParam, 1234);
	message = s.GetBuffer();
	i++;
}
void logMessage2(LPWSTR log, UINT msg, WPARAM wParam, LPARAM lParam)
{
	//theApp.WriteString(_T("ApplicationLook"), log);
	s2.Format(L"%s: I %d M %d W %d L %d D %d", log, i, msg, wParam, lParam, 1234);
	message2 = s2.GetBuffer();
	i++;
}
void logMessage3(LPWSTR log, UINT msg, WPARAM wParam, LPARAM lParam)
{
	//theApp.WriteString(_T("ApplicationLook"), log);
	s3.Format(L"%s: I %d M %d W %d L %d D %d", log, i, msg, wParam, lParam, 1234);
	message3 = s3.GetBuffer();
	i++;
}
HWND activeHWnd = NULL;
HWND activeHWndConfirm = NULL;
void updateActiveWindow()
{
		__wchar_t title[200];
	HWND hWnd = ::GetForegroundWindow();
	if (activeHWndConfirm != hWnd)
	{
		GetWindowText(hWnd, title, sizeof(title) / sizeof(*title));
		if (activeHWnd == hWnd)
		{
			logMessage3(title, 0, (WPARAM)hWnd, (LPARAM)activeHWnd);
			activeHWndConfirm = hWnd;
		} else {
			logMessage(title, 0, (WPARAM)hWnd, (LPARAM)activeHWnd);
			activeHWnd = hWnd;
		}
	} else {
		if (activeHWnd != hWnd){
			GetWindowText(hWnd, title, sizeof(title) / sizeof(*title));
			logMessage2(title, 0, (WPARAM)hWnd, (LPARAM)activeHWnd);
			activeHWnd = hWnd;
		}
	}
}

void CMainFrame::OnTimer(UINT_PTR timer)
{
	updateActiveWindow();
	//CFrameWndEx::OnTimer(timer);
	SetTimer(0, 3000, NULL);
	m_wndView.Invalidate(1);
}

afx_msg LRESULT CMainFrame::MessageHandler(WPARAM wParam, LPARAM lParam)
{
	//logMessage(L"ncactivate", 0, wParam, lParam);
	m_wndView.Invalidate(1);
	return 0L;
}
template <typename T> 
T Hooker(T a, int   nCode, WPARAM   wParam, LPARAM   lParam)
{
	return a;
}

HHOOK mhook;
#define DEF_HOOKER(TYPE) 	HHOOK mhook##TYPE = 0; \
LRESULT   CALLBACK   HookProc##TYPE(int   nCode, WPARAM   wParam, LPARAM   lParam) \
{ \
if (nCode == HC_ACTION)   { \
 \
	CWPSTRUCT* lpCWP = (CWPSTRUCT*)lParam; \
	{ \
	logMessage(L#TYPE, lpCWP->message, wParam, lParam); \
	} \
	\
} \
	\
	return CallNextHookEx(mhook##TYPE, nCode, wParam, lParam); \
} 

#define REF_HOOKER(TYPE) HookProc##TYPE  
#define H_HOOKER(TYPE) mhook##TYPE  

//#define CALL_HOOKER(TYPE) HookProc##TYPE(#TYPE, nCode, wParam, lParam) 

DEF_HOOKER(WH_SYSMSGFILTER)
DEF_HOOKER(WH_CALLWNDPROCRET)
DEF_HOOKER(WH_CBT)
DEF_HOOKER(WH_MOUSE)

void HookNcActivateMessage(HWND hwnd)
{
	//Hooker<int>(1, 1, 1, 1);
	//Hooker<float>(1, 1, 1, 1);
	HINSTANCE hmod = 0;
	DWORD handlerthread = GetWindowThreadProcessId(hwnd, NULL);
	//DWORD handlerthread = 0;
	//H_HOOKER(WH_SYSMSGFILTER) = SetWindowsHookEx(WH_SYSMSGFILTER, REF_HOOKER(WH_SYSMSGFILTER), hmod, handlerthread);
	//H_HOOKER(WH_CALLWNDPROCRET) = SetWindowsHookEx(WH_CALLWNDPROCRET, REF_HOOKER(WH_CALLWNDPROCRET), hmod, handlerthread);
	//H_HOOKER(WH_CBT) = SetWindowsHookEx(WH_CBT, REF_HOOKER(WH_CBT), hmod, handlerthread);
	//H_HOOKER(WH_MOUSE) = SetWindowsHookEx(WH_MOUSE, REF_HOOKER(WH_MOUSE), hmod, handlerthread);
}
void UnhookNcActivateMessage(HWND hwnd)
{
	//Hooker<int>(1, 1, 1, 1);
	//Hooker<float>(1, 1, 1, 1);
	//DWORD handlerthread = GetWindowThreadProcessId(hwnd, NULL);
	DWORD handlerthread = 0;
	UnhookWindowsHookEx(H_HOOKER(WH_SYSMSGFILTER));
	UnhookWindowsHookEx(H_HOOKER(WH_CALLWNDPROCRET));
	UnhookWindowsHookEx(H_HOOKER(WH_CBT));
	UnhookWindowsHookEx(H_HOOKER(WH_MOUSE));
}

#if 0
LRESULT   CALLBACK   HookProc1(int   nCode, WPARAM   wParam, LPARAM   lParam)
{
	if (nCode == HC_ACTION)   {
		CWPSTRUCT* lpCWP = (CWPSTRUCT*)lParam;
		//if (lpCWP->message == WM_NCACTIVATE)
		{
			logMessage(L"1", lpCWP->message, wParam, lParam);
		}

	}

	return 0; // return CallNextHookEx(mhook, nCode, wParam, lParam);
}

LRESULT   CALLBACK   HookProc2(int   nCode, WPARAM   wParam, LPARAM   lParam)
{
	if (nCode == HC_ACTION)   {
		CWPSTRUCT* lpCWP = (CWPSTRUCT*)lParam;
		//if (lpCWP->message == WM_NCACTIVATE)
		{
			logMessage(L"2", lpCWP->message, wParam, lParam);
		}

	}

	return 0; // return CallNextHookEx(mhook, nCode, wParam, lParam);
}

LRESULT   CALLBACK   HookProc3(int   nCode, WPARAM   wParam, LPARAM   lParam)
{
	if (nCode == HC_ACTION)   {
		CWPSTRUCT* lpCWP = (CWPSTRUCT*)lParam;
		//if (lpCWP->message == WM_NCACTIVATE)
		{
			logMessage(L"3", lpCWP->message, wParam, lParam);
		}

	}

	return 0; // return CallNextHookEx(mhook, nCode, wParam, lParam);
}

LRESULT   CALLBACK   HookProc4(int   nCode, WPARAM   wParam, LPARAM   lParam)
{
	if (nCode == HC_ACTION)   {
		CWPSTRUCT* lpCWP = (CWPSTRUCT*)lParam;
		//if (lpCWP->message == WM_NCACTIVATE)
		{
			logMessage(L"4", lpCWP->message, wParam, lParam);
		}

	}

	return 0; // return CallNextHookEx(mhook, nCode, wParam, lParam);
}
#endif
