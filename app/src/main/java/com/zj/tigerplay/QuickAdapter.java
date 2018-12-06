package com.zj.tigerplay;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zj.tigerplay.databinding.ItemTigerBinding;

/**
 * create by zj on 2018/12/5
 */
public class QuickAdapter extends AbsRVAdapter<TigerBean, AbsRVAdapter.BindingViewHolder> {

    public QuickAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding= ItemTigerBinding.inflate(mInflater,viewGroup,false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder bindingViewHolder, int i) {
        ItemTigerBinding binding= (ItemTigerBinding) bindingViewHolder.mBinding;
        TigerBean bean = null;
        if (getDataList().size()!=0){
            bean=getDataList().get(i%getDataList().size());
        }

        if (bean!=null){
            binding.tvContent.setText(bean.content);
        }
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
}
