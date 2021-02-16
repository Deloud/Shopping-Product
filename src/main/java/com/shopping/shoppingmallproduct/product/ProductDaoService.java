package com.shopping.shoppingmallproduct.product;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductDaoService {
    private static List<Product> products = new ArrayList<>();

    //유저 수
    private static int productsCount = 10000;

    //유저 정보들 한눈에 보기
    public List<Product> findAll(){
        return products;
    }

    //유저 저장하기 - 없으면 추가
    public Product save(Product product){
        if (product.getId() == null){
            product.setId(++productsCount);
        }
        products.add(product);
        return product;
    }

    // 이름이나 id로 사람 검색 검색
    public Product findOne(String nameorid){
        for (Product product : products) {
            if(product.getName().equals(nameorid)){
                return product;
            }
            if(product.getId() == ATOI(nameorid)){
                return product;
            }
        }
        return null;
    }

    public Product deleteById(int id){
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()){
            Product product = iterator.next();

            if (product.getId() == id){ // 있으면
                iterator.remove();
                return product;
            }
        }
        return null; //없으면 Null 반환
    }

    // String이면 int로 바꿔주는 함수
    public static int ATOI(String sTmp)
    {
        String tTmp = "0", cTmp = "";

        sTmp = sTmp.trim();
        for(int i=0;i < sTmp.length();i++)
        {
            cTmp = sTmp.substring(i,i+1);
            if(cTmp.equals("0") ||
                    cTmp.equals("1") ||
                    cTmp.equals("2") ||
                    cTmp.equals("3") ||
                    cTmp.equals("4") ||
                    cTmp.equals("5") ||
                    cTmp.equals("6") ||
                    cTmp.equals("7") ||
                    cTmp.equals("8") ||
                    cTmp.equals("9")) tTmp += cTmp;
            else if(cTmp.equals("-") && i==0)
                tTmp = "-";
            else
                break;
        }
        return(Integer.parseInt(tTmp));
    }
}