package vn.tht.launcher.apps

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.tht.launcher.R

data class AppEntry(val label: String, val packageName: String, val icon: Drawable)

object AppsRepository {
    fun load(context: Context): List<AppEntry> {
        val pm = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
        return pm.queryIntentActivities(intent, PackageManager.MATCH_ALL)
            .asSequence()
            .filter { it.activityInfo.packageName != context.packageName }
            .distinctBy { it.activityInfo.packageName }
            .map {
                AppEntry(
                    label = it.loadLabel(pm).toString(),
                    packageName = it.activityInfo.packageName,
                    icon = it.loadIcon(pm),
                )
            }
            .sortedBy { it.label.lowercase() }
            .toList()
    }
}

class AppsAdapter(
    private val onClick: (AppEntry) -> Unit,
    private val onLongClick: (AppEntry) -> Unit,
) : RecyclerView.Adapter<AppsAdapter.VH>() {

    private var items: List<AppEntry> = emptyList()
    private var pinned: Set<String> = emptySet()

    fun submit(list: List<AppEntry>, favorites: Set<String> = pinned) {
        items = list
        pinned = favorites
        notifyDataSetChanged()
    }

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val icon: ImageView = v.findViewById(R.id.icon)
        val label: TextView = v.findViewById(R.id.label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val app = items[position]
        holder.icon.setImageDrawable(app.icon)
        holder.label.text = if (app.packageName in pinned) "★ " + app.label else app.label
        holder.label.setTextColor(vn.tht.launcher.ui.Palette.text)
        holder.itemView.setOnClickListener { onClick(app) }
        holder.itemView.setOnLongClickListener { onLongClick(app); true }
    }
}
