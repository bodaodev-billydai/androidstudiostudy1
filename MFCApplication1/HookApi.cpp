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
#include <Windows.h>
#include <sstream>
#include <stdlib.h>
#include <stdio.h>
#include <tchar.h>
#include <iostream>
#include <string>
#include <shlwapi.h>
#include <shlobj.h>
#include <wctype.h>
#include <winbase.h>
#include <strsafe.h>
#include <conio.h>
#include <psapi.h>
#include <tlhelp32.h>

#pragma comment (lib, "PSAPI.LIB")
#pragma comment (lib, "shlwapi.lib")
#pragma comment (lib, "kernel32.lib")

using namespace std;
typedef void(__cdecl*pfunc)(HWND); 
int e;
#define MY_LIB_2 _T("d:\\MFCLibrary1.dll")
#define MY_LIB_3 _T("d:\\sqlite3.dll")
#define MY_LIB_1 _T("C:\\Users\\yjdai\\Documents\\Visual Studio 2013\\Projects\\ConsoleApplication1\\Debug\\MFCLibrary1.dll")
#define MY_LIB_4 _T("C:\\Users\\yjdai\\Documents\\Visual Studio 2013\\Projects\\ConsoleApplication1\\Debug\\MFCLibrary2.dll")
#define MY_LIB_5 _T("C:\\Users\\yjdai\\Documents\\Visual Studio 2013\\Projects\\ConsoleApplication1\\Debug\\MFCLibrary3.dll")
#define MY_LIB_6 _T("C:\\Users\\yjdai\\Documents\\Visual Studio 2013\\Projects\\ConsoleApplication1\\Debug\\MFCLibrary4.dll")
#define MY_LIB_7 _T("C:\\Users\\yjdai\\Documents\\Visual Studio 2013\\Projects\\ConsoleApplication1\\Debug\\MFCLibrary5.dll")
#define MY_LIB_8 _T("C:\\Users\\yjdai\\Documents\\Visual Studio 2013\\Projects\\ConsoleApplication1\\Debug\\MFCLibrary6.dll")

#define MY_LIB_9 _T("C:\\Users\\yjdai\\Documents\\Visual Studio 2013\\Projects\\ConsoleApplication1\\Debug\\Win32Project1.dll")
void setGlobalHook(HWND hwnd)
{
	HINSTANCE hmod;
	pfunc sethook;
	hmod = LoadLibrary(MY_LIB_9);
	if (hmod != NULL)
	{
		sethook = (pfunc)GetProcAddress(hmod, "SetHook");
		if (sethook != NULL)
		{
			sethook(hwnd);
		}
	}
	else {
		e = errno;
		//MessageBox(0, L"ʧ�ܣ�", L"����", MB_OK);
	}
}
