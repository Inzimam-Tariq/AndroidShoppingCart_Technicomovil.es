package com.web2design.souqclone.app.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.web2design.souqclone.app.R;
import com.web2design.souqclone.app.utils.Utils;
import com.web2design.souqclone.app.model.MenuCategory;
import com.web2design.souqclone.app.model.MenuSubCategory;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Inzimam on 29-Oct-17.
 */

public class ExpandableListAdapterCategory extends BaseExpandableListAdapter {
    
    private List<MenuCategory> dataListHeader;
    private HashMap<MenuCategory, List<MenuSubCategory>> listHashMap;
    private boolean isFilter;
    private Utils utils;
    private Context mContext;
    
    public ExpandableListAdapterCategory(List<MenuCategory> dataListHeader,
                                         HashMap<MenuCategory, List<MenuSubCategory>> listHashMap,
                                         boolean isFilter) {
        
        this.dataListHeader = dataListHeader;
        this.listHashMap = listHashMap;
        this.isFilter = isFilter;
        
    }
    
    @Override
    public int getGroupCount() {
        return dataListHeader.size();
    }
    
    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(dataListHeader.get(groupPosition)).size();
    }
    
    @Override
    public Object getGroup(int groupPosition) {
        return dataListHeader.get(groupPosition);
    }
    
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(dataListHeader.get(groupPosition)).get(childPosition);
    }
    
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    
    @Override
    public boolean hasStableIds() {
        return false;
    }
    
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        mContext = parent.getContext();
        utils = new Utils(mContext);
        MenuCategory menuCategory = (MenuCategory) getGroup(groupPosition);
        
        View groupView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_group, parent, false);
        
        TextView lblListHeader = groupView.findViewById(R.id.lblListHeader);
        lblListHeader.setText(menuCategory.getMenuCategoryName());
//            lblListHeader.setTypeface(null, Typeface.BOLD);
        ImageView imageView = groupView.findViewById(R.id.imageView);
        ImageView expandCollapseIV = groupView.findViewById(R.id.expand_collapse_image);
        
        String imgPath = menuCategory.getMenuCategoryImage();
        utils.printLog("Product Image = " + imgPath);
        if (imgPath != null && !imgPath.isEmpty()) {
            int width = (int) mContext.getResources().getDimension(R.dimen.image_width);
            imageView.getLayoutParams().height = width;
            imageView.getLayoutParams().width = width;
            Picasso.with(parent.getContext()).load(menuCategory.getMenuCategoryImage())
                    .into(imageView);
        } else imageView.setVisibility(View.GONE);
        
        
        if (getChildrenCount(groupPosition) > 0) {
            expandCollapseIV.setImageResource(isExpanded ? R.drawable.ic_expand_less_black : R.drawable.ic_expand_more_black);
        }
        
        if (isFilter) {
            groupView.setClickable(false);
        }
        
        
        return groupView;
    }
    
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, final ViewGroup parent) {
        
        final MenuSubCategory child = (MenuSubCategory) getChild(groupPosition, childPosition);
        View itemView;
        if (!isFilter) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            
            TextView lblListChild = itemView.findViewById(R.id.lblListItem);
            lblListChild.setText(child.getMenuSubCategoryName());
            Log.e("ChildText", child.getMenuSubCategoryName());
            
        } else {
            
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chechbox, parent, false);
            itemView.setClickable(false);
            CheckBox checkBox = itemView.findViewById(R.id.item_option);
            checkBox.setText(child.getMenuSubCategoryName());
//            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    utils.printLog("IsChecked=" + isChecked);
//                    if (isChecked) {
//                        utils.printLog("CheckedId=" + child.getMenuSubCategoryId());
//                        selectedFilters.add(child.getMenuSubCategoryId());
//                    } else {
//                        utils.printLog("Un-CheckedId=" + child.getMenuSubCategoryId());
//                    }
//                }
//            });
        }
        
        return itemView;
    }
    
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
