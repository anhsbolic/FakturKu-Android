package com.fakturku.aplikasi.ui.activity.costumerDetails

import com.fakturku.aplikasi.model.Costumer

interface CostumerDetailsContract{
    interface View{
        fun setCostumerData(costumer: Costumer)

        fun delete(costumer: Costumer)

        fun update(costumer: Costumer)

    }

    interface Presenter{
        fun delete(costumer: Costumer)

        fun update(costumer: Costumer)

    }
}