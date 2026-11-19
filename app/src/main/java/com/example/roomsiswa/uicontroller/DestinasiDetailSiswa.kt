package com.example.roomsiswa.uicontroller

object DestinasiDetailSiswa: DestinasiNavigasi {
    override val route = "detail_siswa"

    override val titleRes = "Detail Siswa"
    const val itemIdArg = "idSiswa"

    val routeWithArgs = "$route/{$itemIdArg}"
}