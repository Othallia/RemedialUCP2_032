package com.example.remedialucp2_032.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.remedialucp2_032.R
import com.example.remedialucp2_032.data.model.BukuLengkap

class BukuAdapter : RecyclerView.Adapter<BukuAdapter.BukuViewHolder>() {

    private var listBuku = emptyList<BukuLengkap>()

    class BukuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvJudul: TextView = view.findViewById(R.id.tvJudulBuku)
        val tvPengarang: TextView = view.findViewById(R.id.tvPengarang)
        val tvKode: TextView = view.findViewById(R.id.tvKode)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_buku, parent, false)
        return BukuViewHolder(view)
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val buku = listBuku[position]

        holder.tvJudul.text = buku.judul
        holder.tvPengarang.text = buku.pengarang
        holder.tvKode.text = "ID: ${buku.uniqueId}"
        holder.tvStatus.text = buku.status

        if (buku.status == "DIPINJAM") {
            holder.tvStatus.setTextColor(Color.RED)
        } else {
            holder.tvStatus.setTextColor(Color.GREEN)
        }
    }

    override fun getItemCount(): Int {
        return listBuku.size
    }

    fun setData(newList: List<BukuLengkap>) {
        listBuku = newList
        notifyDataSetChanged()
    }
}