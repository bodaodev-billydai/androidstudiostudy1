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

// ChildView.cpp : CChildView ���ʵ��
//

#include "stdafx.h"
#include "MFCApplication1.h"
#include "ChildView.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif

LPWSTR message = L"something";
LPWSTR message2 = L"something";
LPWSTR message3 = L"something";
RECT messageholder = { 0, 0, 1000, 30 };
RECT messageholder2 = { 0, 30, 1000, 60 };
RECT messageholder3 = { 0, 60, 1000, 90 };
// CChildView

CChildView::CChildView()
{
}

CChildView::~CChildView()
{
}


BEGIN_MESSAGE_MAP(CChildView, CWnd)
	ON_WM_PAINT()
END_MESSAGE_MAP()



// CChildView ��Ϣ�������

BOOL CChildView::PreCreateWindow(CREATESTRUCT& cs) 
{
	if (!CWnd::PreCreateWindow(cs))
		return FALSE;

	cs.dwExStyle |= WS_EX_CLIENTEDGE;
	cs.style &= ~WS_BORDER;
	cs.lpszClass = AfxRegisterWndClass(CS_HREDRAW|CS_VREDRAW|CS_DBLCLKS, 
		::LoadCursor(NULL, IDC_ARROW), reinterpret_cast<HBRUSH>(COLOR_WINDOW+1), NULL);

	return TRUE;
}

void CChildView::OnPaint() 
{
	CPaintDC dc(this); // ���ڻ��Ƶ��豸������
	
	// TODO:  �ڴ˴������Ϣ����������
	
	// ��ҪΪ������Ϣ������ CWnd::OnPaint()
	dc.DrawText(message, &messageholder, 0);
	dc.DrawText(message2, &messageholder2, 0);
	dc.DrawText(message3, &messageholder3, 0);
}

