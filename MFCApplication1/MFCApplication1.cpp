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

// MFCApplication1.cpp : ����Ӧ�ó��������Ϊ��
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


// CMFCApplication1App ����

CMFCApplication1App::CMFCApplication1App()
{
	SetAppID(_T("Wenxin.Billy.MonitorActiveWindow.0_0_0"));

	// ��������Ҫ�ĳ�ʼ�������� InitInstance ��
}

// Ψһ��һ�� CMFCApplication1App ����

CMFCApplication1App theApp;


// CMFCApplication1App ��ʼ��

BOOL CMFCApplication1App::InitInstance()
{
	CWinAppEx::InitInstance();

	if (!AfxSocketInit())
	{
		AfxMessageBox(IDP_SOCKETS_INIT_FAILED);
		return FALSE;
	}

	// ��ʼ�� OLE ��
	if (!AfxOleInit())
	{
		AfxMessageBox(IDP_OLE_INIT_FAILED);
		return FALSE;
	}

	// ��׼��ʼ��
	// �������ڴ洢���õ�ע�����
	// �����޸�Ϊ��˾����֯��
	SetRegistryKey(_T("WenxinEducation"));

	// ��Ҫ���������ڣ��˴��뽫�����µĿ�ܴ���
	// ����Ȼ��������ΪӦ�ó���������ڶ���
	CMainFrame* pFrame = new CMainFrame;
	if (!pFrame)
		return FALSE;
	m_pMainWnd = pFrame;
	// ���������ؿ�ܼ�����Դ
	pFrame->LoadFrame(IDR_MAINFRAME, 0, NULL, NULL);

	// Ψһ��һ�������ѳ�ʼ���������ʾ����������и���
	pFrame->ShowWindow(SW_SHOW|SW_MINIMIZE);
	pFrame->UpdateWindow();
	return TRUE;
}

int CMFCApplication1App::ExitInstance()
{
	//�����������ӵĸ�����Դ
	AfxOleTerm(FALSE);

	return CWinAppEx::ExitInstance();
}

// CMFCApplication1App ��Ϣ�������

// CMFCApplication1App �Զ������/���淽��

void CMFCApplication1App::PreLoadState()
{
}

void CMFCApplication1App::LoadCustomState()
{
}

void CMFCApplication1App::SaveCustomState()
{
}

// CMFCApplication1App ��Ϣ�������



