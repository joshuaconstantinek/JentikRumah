package com.joshua.r0th.jentikrumah.ui.home;

import com.joshua.r0th.jentikrumah.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump{
    public static HashMap<String, List<Integer>> getData() {
    HashMap<String, List<Integer>> expandableListDetail = new HashMap<>();

    List<List> listOfMixedTypes = new ArrayList<List>();

    List<Integer> cricket = new ArrayList<>();
    cricket.add(R.drawable.satu_rumah_satu_jumantik);


    List<Integer> football = new ArrayList<>();
    football.add(R.drawable.tigamplus);

    List<String> sumber = new ArrayList<>();
    sumber.add("sumber : promkes.kemkes.go.id");

    List<Integer> basketball = new ArrayList<>();
    basketball.add(R.drawable.fogging);

    List<Integer> rocket = new ArrayList<>();
    rocket.add(R.drawable.berantas_sarang_nyamuk);

    listOfMixedTypes.add(cricket);
    listOfMixedTypes.add(sumber);


    expandableListDetail.put("Satu Rumah Satu Jumantik !", cricket);
    expandableListDetail.put("3M plus ! ", football);
    expandableListDetail.put("Ketentuan Melakukan Fogging !", basketball);
    expandableListDetail.put("Berantas Sarang Nyamuk !", rocket);
    return expandableListDetail;
}
}