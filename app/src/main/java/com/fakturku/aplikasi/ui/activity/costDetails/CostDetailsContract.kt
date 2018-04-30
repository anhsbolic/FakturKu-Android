package com.fakturku.aplikasi.ui.activity.costDetails

import com.fakturku.aplikasi.model.Cost

interface CostDetailsContract{

    interface View{
        fun setCostData(cost: Cost)

        fun delete(cost: Cost)

        fun update(cost: Cost)

    }

    interface Presenter{
        fun delete(userId: Long, cost: Cost)

        fun update(cost: Cost)
    }
}