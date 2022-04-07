package com.golda.fitapp.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import com.golda.fitapp.R
import com.golda.fitapp.databinding.DialogBaseInputBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private const val ARG_NUMBER_EAT = "ARG_NUMBER_EAT"
private const val KCAL_VALUE = "KCAL_VALUE"

class BaseDialogInput : BottomSheetDialogFragment() {

    private lateinit var binding: DialogBaseInputBinding
    private var _callback: Callback? = null

    companion object {
        fun newInstance(numb: Int, values: Int = 500): BaseDialogInput {
            return BaseDialogInput().apply {
                arguments = bundleOf(
                    ARG_NUMBER_EAT to numb,
                    KCAL_VALUE to values
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (dialog != null && dialog?.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        binding =
            DialogBaseInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleRes = getString(arguments?.getInt(ARG_NUMBER_EAT)?:0)
        val titleStr = getString(R.string.enter_value, titleRes)
        binding.tvTitle.text = titleStr

            val valueKcal = arguments?.getInt(KCAL_VALUE)?:500
        binding.etValue.setText(valueKcal.toString())

        binding.etValue.doAfterTextChanged {
            val value = binding.etValue.text.toString().toIntOrNull()
            binding.btnApply.isEnabled = value != null && value > 0
        }

        binding.btnApply.setOnClickListener {
            _callback?.applyValue(
                arguments?.getInt(ARG_NUMBER_EAT),
                binding.etValue.text.toString().toInt())
            dismiss()
        }
    }

    fun setCallback(callback: Callback): BaseDialogInput {
        this._callback = callback
        return this
    }

    interface Callback {
        fun applyValue(numberEat: Int?, value: Int)
    }

}