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

#include "MainFrm.h"
#include "Monitor.h"
#include "Syslog.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif

HWND activeHWnd = NULL;
HWND activeHWndConfirm = NULL;
__wchar_t title[2000];
#ifdef _DEBUG
LPWSTR message = L"something";
LPWSTR message2 = L"something";
LPWSTR message3 = L"something";
char message3BS[2000];
RECT messageholder = { 0, 0, 1000, 30 };
RECT messageholder2 = { 0, 30, 1000, 60 };
RECT messageholder3 = { 0, 60, 1000, 90 };
CString s;
CString s2;
CString s3;
int i = 0;
#endif
#define _CRT_SEURE_NO_WARNINGS


#ifdef _DEBUG
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
	size_t converted;
	wcstombs_s(&converted, message3BS, message3, sizeof(message3BS));
	if (converted == 0)
	{
		_wsetlocale(0, L"zh-CN");
		wcstombs_s(&converted, message3BS, message3, sizeof(message3BS));
		//_wsetlocale(0, oldlocal);
	}
	syslog(LOG_INFO, message3BS);
}
void drawDebugMessage(HDC dc)
{
	DrawText(dc, message, (int)wcslen(message), &messageholder, 0);
	DrawText(dc, message2, (int)wcslen(message2), &messageholder2, 0);
	DrawText(dc, message3, (int)wcslen(message3), &messageholder3, 0);
}
#else
//#define  logMessage(log, ...) ((void)0)
//#define  logMessage2(log, ...) ((void)0)
#define  logMessage3(log, ...) ((void)0)
#endif

bool checkActiveWindow()
{
	HWND hWnd = ::GetForegroundWindow();
	if (activeHWndConfirm != hWnd)
	{
		if (activeHWnd == hWnd)
		{
			GetWindowText(hWnd, title, sizeof(title) / sizeof(*title));
			logMessage3(title, 0, (WPARAM)hWnd, (LPARAM)activeHWnd);
			activeHWndConfirm = hWnd;
			return true;
		}
		else {
#ifdef _DEBUG
			GetWindowText(hWnd, title, sizeof(title) / sizeof(*title));
			logMessage(title, 0, (WPARAM)hWnd, (LPARAM)activeHWnd);
#endif
			activeHWnd = hWnd;
			return true;
		}
	} else {
		if (activeHWnd != hWnd){
#ifdef _DEBUG
			GetWindowText(hWnd, title, sizeof(title) / sizeof(*title));
			logMessage2(title, 0, (WPARAM)hWnd, (LPARAM)activeHWnd);
#endif
			activeHWnd = hWnd;
			return true;
		}
	}
	return false;
}
 