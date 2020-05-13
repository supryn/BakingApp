package com.udacity.android.bakingapp.ui.adapter;

import androidx.databinding.ViewDataBinding;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.databinding.StepListItemBinding;
import com.udacity.android.bakingapp.model.Step;

public class StepListTypeAdapter extends BaseListTypeAdapter<Step> {

    StepListTypeAdapter(BakingClickListener clickListener) {
        super(clickListener);
    }

    @Override
    int getLayoutResId() {
        return R.layout.step_list_item;
    }

    @Override
    BaseListTypeViewHolder createListViewHolder(ViewDataBinding binding) {
        return new StepListTypeViewHolder(binding);
    }


    class StepListTypeViewHolder extends BaseListTypeViewHolder {

        private StepListItemBinding mBinding;

        StepListTypeViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(this);
            mBinding = (StepListItemBinding) binding;
        }

        @Override
        void bind(Step step) {
            mBinding.setStep(step);
        }
    }
}
