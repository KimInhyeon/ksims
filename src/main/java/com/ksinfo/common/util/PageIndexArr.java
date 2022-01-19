package com.ksinfo.common.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ksinfo.common.dto.FooterInfoDTO;

public class PageIndexArr {
	
	private static final int FooterLength = 7;
	Boolean blnCheckExist;
	String strRequestURL = "";
	String strGettedURL = "";
	String strGettedQuery = "";

	public void getURLforArray(HttpServletRequest req, 
			String PageName,String UpdtFLG) {
		
		List<FooterInfoDTO> urlArr = null;
		
		HttpSession session = req.getSession();
		//SESSIONから配列の有無を確認、無かった場合は新規配列を。あった場合はそのデータをリターンする
		urlArr = initialize_PageArr(session);
		
		strGettedURL = req.getRequestURL().toString();
		strGettedQuery = req.getQueryString();
		
		if(strGettedQuery==null) {
			//単純な移動処理などの場合
			strRequestURL = strGettedURL;	
		}else if(strGettedQuery!=null) {
			//パラメ-タが存在するURLの処理(社員番号、年月など)
			strRequestURL = strGettedURL  + "?" + strGettedQuery;	
		}
		
		if(urlArr.size()==0 || !urlArr.get(urlArr.size()-1).getArrPageURL().equals(strRequestURL) && urlArr.size()>0) {

			for (int i =0; i<FooterLength-1; i++) {
				
				if(urlArr.size()==0) {
					//情報が無いため、そのまま追加
					putFooterInfo(urlArr, PageName ,strRequestURL, UpdtFLG, i, false);
					break;
				}else if (i<FooterLength-1 && urlArr.size() < FooterLength-1 && UpdtFLG.equals("0")) {//(urlArr.get(i).getPageURL().equals("") && i<FooterLength) {
					//配列の大きさがまだ６個になっていない場合、そのまま情報を追加する
					putFooterInfo(urlArr, PageName ,strRequestURL, UpdtFLG, i, false);
					break;
					
				}else if( FooterLength-2 == i && urlArr.size() <= FooterLength-1 && UpdtFLG.equals("0")) {
					//ページネーションの処理
					if(!urlArr.get(urlArr.size()-1).getArrPageURL().startsWith(strGettedURL)) {
						//配列の大きさが６個になった場合、配列を調整し情報を追加する
						decreaseArrVal(urlArr);
					}
					//空欄になった配列の最後にデータを追加する
					putFooterInfo(urlArr, PageName ,strRequestURL, UpdtFLG, i, true);
					
				}else if (i<FooterLength-1 && UpdtFLG.equals("1")) {
					//配列がまだ想定の大きさになっていない場合、かつDBを使う処理が起きた場合、URLを加工し追加を行わない
					replaceArrVal(urlArr, i);

					break;
					
				}else if(urlArr.size()==FooterLength-1 && FooterLength-2==i && UpdtFLG.equals("1")) {
					//配列が想定の大きさに満たし、DBを使う処理が起きた場合。配列を調整し情報を追加する
					replaceArrVal(urlArr, i-1);
					decreaseArrVal(urlArr);
					putFooterInfo(urlArr, PageName ,strRequestURL, UpdtFLG, i, true);
				}
			}
		}
		//SESSIONに配列を渡す
		session.setAttribute("urlArr", urlArr);
	}
	
	//배열의 데이터를 하나씩 당겨가며 맨 처음에 있던 주소를 삭제하고 공간을 생성함  
	public void decreaseArrVal(List<FooterInfoDTO> urlArr) {

		for (int i =0; i<urlArr.size(); i++) {
			if(i<urlArr.size()-1) {
				//最終データを１個前の配列に保存する
				urlArr.set(i, urlArr.get(i+1)); 
			}		
		}
		//配列の最終を空にして新しい情報を設定出来るようにする 
		urlArr.remove(urlArr.size()-1);
		
	}
	
	public void replaceArrVal(List<FooterInfoDTO> urlArr, int arrIndex) {
		//URLを変更する  
		urlArr.get(arrIndex).setArrPageURL("#");
		
	}
	
	public void putFooterInfo(List<FooterInfoDTO> urlArr, String PageName ,String urlInfo,String UpdtFLG, int i, boolean updProcFlg) {
		//ページネーションの処理
		if(urlArr.size() > 0) {
			if(urlArr.get(urlArr.size()-1).getArrPageURL().startsWith(strGettedURL)) {
				urlArr.remove(urlArr.size()-1);
			}
		}		
		FooterInfoDTO fIDto = new FooterInfoDTO();
		
		fIDto.setArrPageName(PageName);
		fIDto.setArrPageURL(urlInfo);
		fIDto.setArrPageReWriteFLG(UpdtFLG);
		
			if (UpdtFLG.equals("0")) {
				//DBを使わない場合、そのまま追加
				urlArr.add(fIDto);
			}else if(UpdtFLG.equals("1") && !updProcFlg ) {
				//DBを使う場合、かつ配列の情報に更新をかけない場合はそのまま追加
				urlArr.add(fIDto);
				
			}else if(UpdtFLG.equals("1") && updProcFlg ) {
				//DBを使う場合、かつ配列に更新が起きる場合は配列の順番を調整する
				if(i == 0 ) {
					//配列にデータが１個しか無かった場合はそのまま追加する。
					replaceArrVal(urlArr, i);
				}else if(i>0) {
					//URLを変更しリンク先に飛ばないようにする
					replaceArrVal(urlArr, i-1);
				}
			}
		
	}

	public List<FooterInfoDTO> initialize_PageArr(HttpSession session) {
				
		Enumeration<?> en = session.getAttributeNames();
		//フラグがTRUEになる場合、SESSIONに配列がすでに存在するケースになる。
		blnCheckExist = false;
		
		while (en.hasMoreElements()) {
			String ItemName = (String) en.nextElement();
			//SESSIONに配列が存在するかチェックし存在する場合、そのアトリビュートをリターンする。
			if(ItemName.equals("urlArr")) {
				return (List<FooterInfoDTO>) session.getAttribute("urlArr");
			}
		}
		//配列が存在しない場合、新しい配列を作成しリターンする。
		return new ArrayList<FooterInfoDTO>(0);
	}
	
}

