/**
 * 
 */
package com.darly.dlclent.widget.floorview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darly.dlclent.R;


/**
 * @ClassName: 	SubFloorFactory
 * @Description:TODO
 * @author 	JohnnyShieh
 * @date	Feb 20, 2014		10:10:01 AM
 */
public class SubFloorFactory {

    public View buildSubFloor ( Comment cmt, ViewGroup group ) {
        LayoutInflater inflater = ( LayoutInflater ) group.getContext ().getSystemService ( Context.LAYOUT_INFLATER_SERVICE ) ;
        View view = inflater.inflate ( R.layout.comment_sub_floor, null ) ;
        RelativeLayout show = ( RelativeLayout ) view.findViewById ( R.id.show_sub_floor_content ) ;
        RelativeLayout hide = ( RelativeLayout ) view.findViewById ( R.id.hide_sub_floor_content ) ;
        show.setVisibility ( View.VISIBLE ) ;
        hide.setVisibility ( View.GONE ) ;
        TextView floorNum = ( TextView ) view.findViewById ( R.id.sub_floor_num ) ;
        TextView username = ( TextView ) view.findViewById ( R.id.sub_floor_username ) ;
        TextView content = ( TextView ) view.findViewById ( R.id.sub_floor_content ) ;
        floorNum.setText ( String.valueOf ( cmt.getFloorNum () ) ) ;
        username.setText ( cmt.getUserName () ) ;
        content.setText ( cmt.getContent () ) ;
        return view ;
    }

    public View buildSubHideFloor ( Comment cmt, ViewGroup group ) {
        LayoutInflater inflater = ( LayoutInflater ) group.getContext ().getSystemService ( Context.LAYOUT_INFLATER_SERVICE ) ;
        View view = inflater.inflate ( R.layout.comment_sub_floor, null ) ;
        RelativeLayout show = ( RelativeLayout ) view.findViewById ( R.id.show_sub_floor_content ) ;
        RelativeLayout hide = ( RelativeLayout ) view.findViewById ( R.id.hide_sub_floor_content ) ;
        show.setVisibility ( View.GONE ) ;
        hide.setVisibility ( View.VISIBLE ) ;
        TextView hide_text = ( TextView ) view.findViewById ( R.id.hide_text ) ;
        hide_text.setCompoundDrawablesWithIntrinsicBounds ( R.drawable.biz_tie_comment_expand_arrow, 0, 0, 0 ) ;
        view.findViewById ( R.id.hide_pb ).setVisibility ( View.GONE ) ;
        return view ;
    }
}
