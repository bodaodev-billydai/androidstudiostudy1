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

// MFCApplication1.cpp : 定义应用程序的类行为。
//

#include "stdafx.h"
#include "MFCApplication1.h"
#include "MainFrm.h"


#ifdef _DEBUG
#define new DEBUG_NEW
#endif

// CMFCApplication1App

BEGIN_MESSAGE_MAP(CMFCApplication1App, CWinAppEx)
END_MESSAGE_MAP()


// CMFCApplication1App 构造

CMFCApplication1App::CMFCApplication1App()
{
	SetAppID(_T("Wenxin.Billy.MonitorActiveWindow.0_0_0"));

	// 将所有重要的初始化放置在 InitInstance 中
}

// 唯一的一个 CMFCApplication1App 对象

CMFCApplication1App theApp;


// CMFCApplication1App 初始化

BOOL CMFCApplication1App::InitInstance()
{
	CWinAppEx::InitInstance();

	if (!AfxSocketInit())
	{
		AfxMessageBox(IDP_SOCKETS_INIT_FAILED);
		return FALSE;
	}

	// 初始化 OLE 库
	if (!AfxOleInit())
	{
		AfxMessageBox(IDP_OLE_INIT_FAILED);
		return FALSE;
	}

	// 标准初始化
	// 更改用于存储设置的注册表项
	// 例如修改为公司或组织名
	SetRegistryKey(_T("WenxinEducation"));

	// 若要创建主窗口，此代码将创建新的框架窗口
	// 对象，然后将其设置为应用程序的主窗口对象
	CMainFrame* pFrame = new CMainFrame;
	if (!pFrame)
		return FALSE;
	m_pMainWnd = pFrame;
	// 创建并加载框架及其资源
	pFrame->LoadFrame(IDR_MAINFRAME, 0, NULL, NULL);

	// 唯一的一个窗口已初始化，因此显示它并对其进行更新
	pFrame->ShowWindow(SW_SHOW|SW_MINIMIZE);
	pFrame->UpdateWindow();
	return TRUE;
}

int CMFCApplication1App::ExitInstance()
{
	//处理可能已添加的附加资源
	AfxOleTerm(FALSE);

	return CWinAppEx::ExitInstance();
}

// CMFCApplication1App 消息处理程序

// CMFCApplication1App 自定义加载/保存方法

void CMFCApplication1App::PreLoadState()
{
}

void CMFCApplication1App::LoadCustomState()
{
}

void CMFCApplication1App::SaveCustomState()
{
}

// CMFCApplication1App 消息处理程序



