package hr.factory.epoxy_models

import android.view.LayoutInflater
import androidx.core.view.ViewCompat
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.TypedEpoxyController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import hr.factory.R
import hr.factory.databinding.CellChipGroupBinding
import hr.factory.ui_models.ChipGroupCellData

@EpoxyModelClass
abstract class ChipGroupppModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    lateinit var chops: List<String>

    override fun getDefaultLayout() = R.layout.cell_chip_group

    override fun setDataBindingVariables(binding: ViewDataBinding?) {

        (binding as? CellChipGroupBinding)?.apply {
            addChipsCells(this.chipGroup)
        }
    }

    // add chips to chip group
    private fun addChipsCells(chipGroup: ChipGroup){
        chipGroup.removeAllViews()

        val inflater = LayoutInflater.from(chipGroup.context)

        chops.forEachIndexed { index, label ->
            val chip = inflater.inflate(R.layout.view_chip, chipGroup, false) as Chip

            chip.id = index
            chip.text = label
            chip.isAllCaps = true
            chip.isCheckable = false

            ViewCompat.setElevation(chip, 0.0f)

            chipGroup.addView(chip)
        }
    }
}

fun TypedEpoxyController<*>.addChipGroup(cell: ChipGroupCellData) {
    chipGrouppp {
        id("chipGroup")

        chops(cell.chips)
    }
}