package com.cdp.pdfdocumentdemo

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT

class BaseDialog(context: Context, var layoutID:Int) : Dialog(context, R.style.CustomDialog) {
    private var displayListener: DisplayListener? = null

    private var gravity: Int = Gravity.CENTER

    private var contentWasLoad: ContentWasLoad? = null

    private var cancelAble: Boolean = false

    private var canTouchOutside: Boolean = false

    private var animations:Int?=null

    private var isShow: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view: View = LayoutInflater.from(context).inflate(layoutID, null)

        setContentView(view)
        setCancelable(cancelAble)
        setCanceledOnTouchOutside(canTouchOutside)
        window?.setGravity(gravity)
        window?.setLayout(WRAP_CONTENT,WRAP_CONTENT)
        animations?.let { window?.setWindowAnimations(it) }
        contentWasLoad?.onDialogLoad(view)
        contentWasLoad?.initViewEvent()


        setOnCancelListener {
            displayListener?.onCancel()
        }

        setOnDismissListener {
            displayListener?.onDismiss()
        }

        setOnShowListener {
            displayListener?.onShow()
        }
    }


    override fun show() {
        if (!isShow) {
            isShow = true
            super.show()
        }
    }

    override fun dismiss() {
        if (isShow) {
            isShow = false
            super.dismiss()
        }
    }


    interface DisplayListener {
        fun onShow()

        fun onDismiss()

        fun onCancel()
    }

    interface ContentWasLoad {
        fun onDialogLoad(view: View)

        fun initViewEvent()
    }


    class Builder(context: Context, layoutID:Int) {
        private var dialog: BaseDialog? = null

        private var displayListener: DisplayListener? = null

        private var gravity: Int = Gravity.CENTER

        private var contentWasLoad: ContentWasLoad? = null

        private var cancelAble: Boolean = false

        private var canTouchOutside: Boolean = false

        private var animations:Int?=null


        init {
            dialog = BaseDialog(context,layoutID)
        }

        fun setDisplayListener(listener: DisplayListener): Builder {
            this.displayListener = listener
            return this
        }

        fun setGravity(gravity: Int): Builder {
            this.gravity = gravity
            return this
        }


        fun onContentViewWasLoad(contentWasLoad: ContentWasLoad): Builder {
            this.contentWasLoad = contentWasLoad
            return this
        }

        fun setCancelAble(cancelAble: Boolean): Builder {
            this.cancelAble = cancelAble
            return this
        }

        fun setCanTouchOutSide(canTouchOutside: Boolean): Builder {
            this.canTouchOutside = canTouchOutside
            return this
        }

        fun setAnimations(animations: Int): Builder {
            this.animations = animations
            return this
        }


        fun build(): BaseDialog {
            dialog?.cancelAble = cancelAble
            dialog?.canTouchOutside = canTouchOutside
            dialog?.displayListener = displayListener
            dialog?.gravity = gravity
            dialog?.contentWasLoad = contentWasLoad
            dialog?.animations = animations
            return dialog!!
        }

    }
}