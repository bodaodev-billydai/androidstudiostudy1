// 这段 MFC 示例源代码演示如何使用 MFC Microsoft Office Fluent 用户界面 
// (“Fluent UI”)。该示例仅供参考，
// 用以补充《Microsoft 基础类参考》和 
// MFC C++ 库软件随附的相关电子文档。  
// 复制、使用或分发 Fluent UI 的许可条款是单独提供的。  
// 若要了解有关 Fluent UI 许可计划的详细信息，请访问  
// http://go.microsoft.com/fwlink/?LinkId=238214。
//
// 版权所有(C) Microsoft Corporation
// 保留所有权利。

// MainFrm.cpp : CMainFrame 类的实现
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
		//MessageBox(0, L"失败！", L"标题", MB_OK);
	}
}
