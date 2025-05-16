package com.treasure.ledger.func.assets

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.treasure.basic.utils.SpUtils
import com.treasure.basic.utils.ToastUtils
import com.treasure.ledger.R
import com.treasure.ledger.data.entity.AssetEntity
import com.treasure.ledger.utils.Constants


class AssetInsertDialogFragment(private val onAssetAdded: (AssetEntity) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomDialog)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_add_asset, null)
        builder.setView(view)

        val etName = view.findViewById<EditText>(R.id.etAssetName)
        val etAmount = view.findViewById<EditText>(R.id.etAssetAmount)
        val btnAdd = view.findViewById<Button>(R.id.btnAddAsset)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)

        tvTitle.text = "添加明细"

        btnAdd.setOnClickListener {
            val name = etName.text.toString().trim()
            val amount = etAmount.text.toString().toDoubleOrNull() ?: 0.0

            if (name.isNotEmpty()) {
                val selSections = when (view.findViewById<RadioGroup>(R.id.rg_section).checkedRadioButtonId) {
                    R.id.rb_bank -> "bank"
                    R.id.rb_payment -> "payment"
                    R.id.rb_recharge -> "recharge"
                    else -> ""
                }
                val uid = SpUtils.getString(Constants.KEY_SP_LOGIN_UID)
                val asset = AssetEntity(uid = uid, section = selSections, name = name, amount = amount, iconRes = R.drawable.ic_assets)
                onAssetAdded(asset)
                dismiss()
            } else {
                ToastUtils.show(requireContext(), "请输入资产名称")
            }
        }

        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.85).toInt(),  // 宽度占屏幕的85%
            (resources.displayMetrics.heightPixels * 0.55).toInt(),   // 高度自适应
        )
    }

}
