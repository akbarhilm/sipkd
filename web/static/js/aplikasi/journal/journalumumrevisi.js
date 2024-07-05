$(document).ready(function() {
    getnojurnal();
    $("#tglPosting").datepicker("setDate", new Date());
    $("#tglDok").datepicker("setDate", new Date());
    statusupdate = 0;
    getCurrentBulan();

});

var idrow = 0;
var idbuton;
var nomorjurnal;
var tglket;
var tanggalpost;
var tanggaldok;
var idjurnal = new Array();
var nilaijenis = new Array();
var banyakdata;
var banyakdataawal;
var statusupdate = 0; // 0 = insert, 1 = insert, update
var nojurnaldok;
var currentbulan;

function getCurrentBulan() {
    currentbulan = $("#tglPosting").val();
    var mm = currentbulan.substr(3, 2);
    //console.log("Current bulan ===== " + mm);

    if (mm == "04" || mm == "05" || mm == "06") {
        document.getElementById("koreksidariBPK").checked = true;
    }
}

function tambahRow() {
    idrow += 1;
    //bootbox.alert("id row == "+idrow);

    var table = document.getElementById('jourtablebody');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    var cell6 = row.insertCell(5);
    var cell7 = row.insertCell(6);
    var cell8 = row.insertCell(7);
    var cell9 = row.insertCell(8);
    var cell10 = row.insertCell(9);
    var cell11 = row.insertCell(10);

    cell1.innerHTML = "<td class= 'center' >" + idrow + "</td>";
    cell2.innerHTML = "<td class='center'><input type='hidden' id='idskpd" + idrow + "' name='idskpd" + idrow + "' > <textarea type='text' id='ketskpd" + idrow + "' name='ketskpd" + idrow + "' readonly='true' style='margin: 0px; width: 155px; height: 42px;' ></textarea> <a id='pilih" + idrow + "'  class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + ")' href='" + getbasepath() + "/journal/listskpd?target='_top'' title='Pilih SKPD'  ><i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell3.innerHTML = "<td class='center'><input type='text' id='kodeakun" + idrow + "' size='12' name='kodeakun" + idrow + "' onkeypress='cariakun(this.value,this.input," + idrow + "),setPilihkegiatan(this.value," + idrow + ")'> <input type='hidden' id='idbas" + idrow + "' name='idbas" + idrow + "' > <a id='akunpilih" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + ")' href='" + getbasepath() + "/akun/listakun?target='_top'' title='Pilih Kode Akun'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell4.innerHTML = "<td class='center'><textarea  id='namaakun" + idrow + "' name='namaakun" + idrow + "' class='m-wrap large valid' readonly='true' style='margin: 0px; width: 130px; height: 42px;' > </textarea> </td>";

    cell5.innerHTML = "<td class='center'><select id='koreksi" + idrow + "' name='koreksi" + idrow + "' onchange='setKoreksiDebetKredit(" + idrow + ")' ><option value='1'>Penambahan</option><option value='2'>Pengurangan</option></select> </td>";

    cell6.innerHTML = "<td> <input type='text' name='debet" + idrow + "' id='debet" + idrow + "' class='inputmoney' value = '0' readonly='true' onkeyup='getTotal(),validasiDebet(" + idrow + ")' onclick = 'validasiDebet(" + idrow + ")' /> </td>";
    cell7.innerHTML = "<td> <input type='text' name='kredit" + idrow + "' id='kredit" + idrow + "' class='inputmoney' value = '0' readonly='true' onkeyup='getTotal(),validasiKredit(" + idrow + ")' onclick = 'validasiKredit(" + idrow + ")' /> </td>";
    cell8.innerHTML = "<td class='center'><select id='jenis" + idrow + "' name='jenis" + idrow + "' onchange='setkriteria(this.value," + idrow + ")' > </select> </td>";
    cell9.innerHTML = "<td class='center'><select id='beban" + idrow + "' name='beban" + idrow + "' ><option></option></select> </td>";
    cell10.innerHTML = "<td class ='center'><input type='text' id='kegiatan" + idrow + "' name='kegiatan" + idrow + "' readonly='true' size='15' > <input type='hidden' id='idkegiatan" + idrow + "' name='idkegiatan" + idrow + "' > <a id='pilihkegiatan" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilakun(" + idrow + ")' href='" + getbasepath() + "/kegiatan/listkegiatan?target='_top'' title='Pilih Kode Akun'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell11.innerHTML = "<td class ='center'><textarea  id='namakegiatan" + idrow + "' name='namakegiatan" + idrow + "' class='m-wrap large valid' readonly='true' style='margin: 0px; width: 130px; height: 42px;'> </textarea> </td>";

    formatnumberonkeyup();
    document.getElementById("pilihkegiatan" + idrow).style.visibility = "hidden";
}

function setPilihkegiatan(nilai, id) {
    var jenis = $('#jenis' + id).val();
    document.getElementById("kegiatan" + id).readOnly = true;

    if (jenis == '3') {
        if (nilai == "") {
            document.getElementById("pilihkegiatan" + id).style.visibility = "hidden";
        } else {
            document.getElementById("pilihkegiatan" + id).style.visibility = "visible";
        }
    }
}

function getbutton(id) {
    idbuton = id;
}

function getidskpd() {
    //bootbox.alert("CEK NAMA SKPD POP UP = "+$('#skpdpop').val());
    var id = $('#idskpdpop').val();
    var keterangan = $('#skpdpop').val();

    $('#idskpd' + idbuton).val(id);
    $('#ketskpd' + idbuton).val(keterangan);

}

function getidakun() {
    var id = idbuton;

    $('#idbas' + id).val($('#idbaspop').val());
    $('#kodeakun' + id).val($('#idakunpop').val());
    $('#namaakun' + id).val($('#namaakunpop').val());

    setPilihkegiatan($('#idakunpop').val(), id);
    setComboJenis();
    setKoreksiDebetKredit(id);

}

function setKoreksiDebetKredit(id) {
    var kodeakun = $('#kodeakun' + id).val();
    var koreksi = $('#koreksi' + id).val(); // 1-Penambahan, 2-Pengurangan
    var nilaisebelum;
    //console.log("idbuton == " + id);
    //console.log("kode akun = " + kodeakun);
    //console.log("koreksi === " + koreksi);

    if (kodeakun !== "") {
        var kode = kodeakun.substr(0, 1);
        var kode2 = kodeakun.substr(0, 3);

        if (kode == "1" || kode == "5" || kode == "9" || kode2 == "6.2") {
            if (koreksi == 1) { // Penambahan
                document.getElementById("kredit" + id).readOnly = true; // tdk aktif
                document.getElementById("debet" + id).readOnly = false; // aktif
                nilaisebelum = $('#kredit' + id).val();
                $('#debet' + id).val(nilaisebelum);
                $('#kredit' + id).val(0);
                
            } else { // Pengurangan
                document.getElementById("kredit" + id).readOnly = false;
                document.getElementById("debet" + id).readOnly = true;
                nilaisebelum = $('#debet' + id).val();
                $('#kredit' + id).val(nilaisebelum);
                $('#debet' + id).val(0);
                
            }
        } else if (kode == "2" || kode == "3" || kode == "4" ||kode == "8" || kode2 == "6.1") {
            if (koreksi == 1) { // Penambahan
                document.getElementById("kredit" + id).readOnly = false;
                document.getElementById("debet" + id).readOnly = true;
                nilaisebelum = $('#debet' + id).val();
                $('#kredit' + id).val(nilaisebelum);
                $('#debet' + id).val(0);
                
            } else { // Pengurangan
                document.getElementById("kredit" + id).readOnly = true;
                document.getElementById("debet" + id).readOnly = false;
                nilaisebelum = $('#kredit' + id).val();
                $('#debet' + id).val(nilaisebelum);
                $('#kredit' + id).val(0);
                
            }
        }
        
        getTotal();
    }

}

function getkegiatan() {
    var id = idbuton;

    $('#idkegiatan' + id).val($('#idkegpop').val());
    $('#kegiatan' + id).val($('#kodekegpop').val());
    $('#namakegiatan' + id).val($('#namakegpop').val());
}

function getTotal() {
    var i, totdeb, totkre, nilaid, nilaik;
    totdeb = 0;
    totkre = 0;

    for (i = 1; i <= idrow; i++) {
        if ($('#debet' + i).val() == "") {
            nilaid = 0;
        } else {
            nilaid = parseFloat(accounting.unformat($('#debet' + i).val(), ","));
        }

        if ($('#kredit' + i).val() == "") {
            nilaik = 0;
        } else {
            nilaik = parseFloat(accounting.unformat($('#kredit' + i).val(), ","));
        }

        totdeb = totdeb + nilaid;
        totkre = totkre + nilaik;
    }

    $('#totaldebet').val(accounting.formatNumber(totdeb));
    $('#totalkredit').val(accounting.formatNumber(totkre));
}

function validasiDebet(i) {
    if ($('#debet' + i).val() != 0) {
        parseFloat(accounting.unformat($('#kredit' + i).val(0), ","));
        getTotal();
    }
}

function validasiKredit(i) {
    if ($('#kredit' + i).val() != 0) {
        parseFloat(accounting.unformat($('#debet' + i).val(0), ","));
        getTotal();
    }
}

function setUlangJenis(nilai, id) {
    var optjenis;

    if (nilai == 1) {
        optjenis = '<option value="1">BTL</option>';
    } else if (nilai == 2) {
        optjenis = '<option value="2">BTL BANTUAN</option>';
    } else if (nilai == 3) {
        optjenis = '<option value="3">BL</option>';
    } else if (nilai == 4) {
        optjenis = '<option value="4">BIAYA</option>';
    } else if (nilai == 5) {
        optjenis = '<option value="5">PENERIMAAN</option>';
    } else if (nilai == 9) {
        optjenis = '<option value="9">LAIN-LAIN</option>';
    }

    $("#jenis" + id).html(optjenis);
}

function setkriteria(kodejenis, jenis) {
    var id = jenis;
    var opttemuan = '<option value="LS">Langsung</option>';

    document.getElementById("pilihkegiatan" + id).style.visibility = "hidden";
    document.getElementById("kegiatan" + id).readOnly = true;

    if (kodejenis == '3') {
        document.getElementById("pilihkegiatan" + id).style.visibility = "visible";
        opttemuan += '<option value="UP">UP/GU</option>';
        opttemuan += '<option value="TU">TU</option>';
    }

    if (kodejenis == '5' || kodejenis == '9') {
        var opttemuan = '<option value="">-</option>';
    }

    $("#beban" + id).html(opttemuan);
}

function cariakun(kode, evt, nama) {
    var e = evt || window.event;
    var key = e.keyCode || e.which;

    if (!e.shiftKey && !e.altKey && !e.ctrlKey &&
            key == 13) {
        getnama(kode, nama);
    }
}

function getnama(kode, nama) {
    $.getJSON(getbasepath() + "/journal/json/getnamaakun", {kode: kode},
    function(result) {
        var id = nama;

        if (result.aData.length > 0) {
            var namaakun = result.aData[0]['namaakun'];
            var idbas = result.aData[0]['idBas'];

            $('#namaakun' + id).val(namaakun);
            $('#idbas' + id).val(idbas);

            //bootbox.alert("KODE AKUN = " + kode + " -- NAMA AKUN = " + namaakun + " -- IDBAS = " + idbas);

        } else {
            bootbox.alert("Kode Akun Tidak Ada");
            $('#namaakun' + id).val("");
        }

    });
}

function getnojurnal() {
    var tahun = $("#tahunAngg").val();
    $.getJSON(getbasepath() + "/journal/json/getnojurnal", {tahun: tahun},
    function(result) {

        var nojur = result.aData['noJournal'];
        //$("#noJournal").val(nojur);
        nomorjurnal = nojur;
        $("#nojurhidden").val(nojur);
    });
}

function formattanggal(tgl) {
    var yy = tgl.substring(6);
    var mm = tgl.substr(3, 2);
    var dd = tgl.substr(0, 2);

    var tglgabung = yy + mm + dd;

    return tglgabung;
}

function settglket() {
    var tgl = $("#tglPosting").val();
    var yy = tgl.substring(8);
    var mm = tgl.substr(3, 2);

    tglket = yy + mm;
}

function cektotal() {
    if (statusupdate == 0) {
        simpan();
    } else if (statusupdate == 1) {
        update();
    }
}

function update() {

    var koreksiBpk;

    if (document.getElementById("koreksidariBPK").checked == true) {
        koreksiBpk = "1"; //console.log("Checked true 1"); 
    } else if (document.getElementById("koreksidariBPK").checked == false) {
        koreksiBpk = "0"; //console.log("Checked false 0"); 
    }


    var tahun = $("#tahunAngg").val();
    $("#noJournal").val();
    settglket();
    //bootbox.alert("UPDATE -- NO JURNAL == "+$("#noJournal").val());
    var tkredit = $('#totalkredit').val();
    var tdebet = $('#totaldebet').val();
    var tglpost = $('#tglPosting').val();

    var ketidskpd = true;
    var ketidbas = true;

    for (var a = 1; a <= idrow; a++) {
        if ($('#idbas' + a).val() == "") {
            ketidbas = false;
        }
        if ($('#idskpd' + a).val() == "") {
            ketidskpd = false;
        }
    }

    if (tkredit == tdebet) {
        if (tglpost == "") {
            bootbox.alert("Isi Tanggal Jurnal Terlebih Dulu");
        } else if (ketidskpd == false) {
            bootbox.alert("Data SKPD Tidak Boleh Kosong");
        } else if (ketidbas == false) {
            bootbox.alert("Data Akun Tidak Boleh Kosong");
        } else {
            //bootbox.alert("Masuk ke simpan");

            var varurl = getbasepath() + "/journal/json/prosessimpanupdate";
            var dataac = [];
            var nilainr = [];
            var nilailist = [];
            var nilainojurnal = [];
            var i;
            var banyakNR;
            var bebann;
            var jumNR = 0;
            var jumlist = 0;
            var idjurnalkosong = new Array();

            banyakNR = idrow - banyakdataawal;

            for (i = 1; i <= banyakdataawal; i++) { // untuk hitung banyak debet kredit = 0, untuk update c_aktif
                var nb2, nd2;
                nb2 = parseFloat(accounting.unformat($('#debet' + i).val(), ","));
                nd2 = parseFloat(accounting.unformat($('#kredit' + i).val(), ","));

                if ($('#debet' + i).val() == "") {
                    nb2 = 0;
                }

                if ($('#kredit' + i).val() == "") {
                    nd2 = 0;
                }

                if ((nb2 == 0) && (nd2 == 0)) {
                    idjurnalkosong[jumlist] = idjurnal[i]
                    jumlist += 1;
                }
            }

            //bootbox.alert("Jumlah list kosong = " + jumlist);

            for (i = 0; i < jumlist; i++) {
                var id3 = i + 1;
                var pararr1 = {idjur: idjurnalkosong[i]
                };
                nilainojurnal[i] = pararr1;
            }

            for (i = 0; i < banyakdataawal; i++) { // list
                var id2 = i + 1;
                bebann = $("#beban" + id2).val();
                if (bebann == null) {
                    bebann = "";
                } else {
                    bebann = $("#beban" + id2).val();
                }

                var pararr2 = {idskpd: $("#idskpd" + id2).val(),
                    idjur: idjurnal[id2],
                    idbas: $("#idbas" + id2).val(),
                    debet: (accounting.unformat($("#debet" + id2).val(), ",")),
                    kredit: (accounting.unformat($("#kredit" + id2).val(), ",")),
                    beban: bebann, //$("#beban" + id).val(),
                    kegiatan: $("#idkegiatan" + id2).val(),
                    jenis: $("#jenis" + id2).val()
                };
                nilailist[i] = pararr2;
            }

            for (i = 0; i < banyakNR; i++) { // list
                var id = banyakdataawal + i + 1;
                var nb, nd;
                nb = $("#debet" + id).val();
                nd = $("#kredit" + id).val();

                if ((nb !== "") && (nd !== "")) {
                    var pararr = {idskpd: $("#idskpd" + id).val(),
                        idbas: $("#idbas" + id).val(),
                        debet: (accounting.unformat($("#debet" + id).val(), ",")),
                        kredit: (accounting.unformat($("#kredit" + id).val(), ",")),
                        beban: $("#beban" + id).val(),
                        kegiatan: $("#idkegiatan" + id).val(),
                        jenis: $("#jenis" + id).val()
                    };
                    nilainr[jumNR] = pararr;
                    jumNR += 1;
                }

            }

            var datajour = {
                tglket: tglket,
                jumlah: idrow,
                banyakNR: jumNR,
                tglPosting: formattanggal($("#tglPosting").val()),
                noDok: $("#noDok").val(),
                tglDok: formattanggal($("#tglDok").val()),
                noJournal: $("#noJournal").val(),
                ketJour: $("#ketJour").val(),
                nojourdok: nojurnaldok,
                nilainr: nilainr,
                nilailist: nilailist,
                jumkosong: jumlist,
                nilainojurnal: nilainojurnal,
                koreksiBpk: koreksiBpk
            }
            dataac = datajour;

            return   $.ajax({
                type: "POST",
                url: varurl,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(dataac)
            }).always(function(data) {
                clearrow();
                bootbox.alert("Data Jurnal Umum SKPD Berhasil Disimpan");
            });
        }
    } else {
        bootbox.alert("Data Jurnal Gagal Disimpan (Total Debet Tidak Sama Dengan Total Kredit)");
    }

}

function simpan() {

    var koreksiBpk;

    if (document.getElementById("koreksidariBPK").checked == true) {
        koreksiBpk = "1"; //console.log("Checked true 1"); 
    } else if (document.getElementById("koreksidariBPK").checked == false) {
        koreksiBpk = "0"; //console.log("Checked false 0"); 
    }

    var tahun = $("#tahunAngg").val();
    $("#noJournal").val($("#nojurhidden").val());
    settglket();

    var tkredit = $('#totalkredit').val();
    var tdebet = $('#totaldebet').val();
    var tglpost = $('#tglPosting').val();

    var ketidskpd = true;
    var ketidbas = true;

    for (var a = 1; a <= idrow; a++) {
        if ($('#idbas' + a).val() == "") {
            ketidbas = false;
        }
        if ($('#idskpd' + a).val() == "") {
            ketidskpd = false;
        }
    }


    if (tkredit == tdebet) {
        if (tglpost == "") {
            bootbox.alert("Isi Tanggal Jurnal Terlebih Dulu");
        } else if (ketidskpd == false) {
            bootbox.alert("Data SKPD Tidak Boleh Kosong");
        } else if (ketidbas == false) {
            bootbox.alert("Data Akun Tidak Boleh Kosong");
        } else {
            //bootbox.alert("Masuk ke simpan");

            var varurl = getbasepath() + "/journal/json/prosessimpan";
            var dataac = [];
            var nilainr = [];
            var i;
            var p = 0;

            for (i = 0; i < idrow; i++) { // list
                var id = i + 1;
                var nb, nd;
                nb = $("#debet" + id).val();
                nd = $("#kredit" + id).val();

                if ((nb !== "") && (nd !== "")) {
                    var pararr = {idskpd: $("#idskpd" + id).val(),
                        idbas: $("#idbas" + id).val(),
                        debet: (accounting.unformat($("#debet" + id).val(), ",")),
                        kredit: (accounting.unformat($("#kredit" + id).val(), ",")),
                        beban: $("#beban" + id).val(),
                        kegiatan: $("#idkegiatan" + id).val(),
                        jenis: $("#jenis" + id).val()
                    };
                    nilainr[p] = pararr;
                    p += 1;
                }

            }

            var datajour = {
                tglket: tglket,
                jumlah: idrow,
                tglPosting: formattanggal($("#tglPosting").val()),
                noDok: $("#noDok").val(),
                tglDok: formattanggal($("#tglDok").val()),
                noJournal: nomorjurnal,
                ketJour: $("#ketJour").val(),
                nilainr: nilainr,
                koreksiBpk: koreksiBpk
            }
            dataac = datajour;

            return   $.ajax({
                type: "POST",
                url: varurl,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(dataac)
            }).always(function(data) {
                clearrow();
                getnojurnal();
                bootbox.alert("Data Jurnal Umum SKPD Berhasil Disimpan");

            });
        }

    } else {
        bootbox.alert("Data Jurnal Gagal Disimpan (Total Debet Tidak Sama Dengan Total Kredit)");
    }
}


function setjenis(id, jenis) {
    document.getElementById("jenis" + id).value = jenis;
}

function getbanyakrinci() {
    $.getJSON(getbasepath() + "/journal/json/getlistbanyakrinci", {noJournal: $("#noJournal").val()},
    function(result) {
        banyakdata = result;
        banyakdataawal = result;

        $('#banyakrinci').val(result);

        statusupdate = 1;
        getrincilist();
    });
}

function getrincilist() {
    $.getJSON(getbasepath() + "/journal/json/valtabel", {noJournal: $("#noJournal").val()},
    function(result) {
        var i, c, jenis, beban;
        var td = 0;
        var tk = 0;

        idrow = 0;
        for (i = 1; i <= banyakdata; i++) {
            tambahRow();
        }

        for (i = 0; i < banyakdataawal; i++) {
            c = i + 1;

            idjurnal[c] = result.aData[i]['idJour'];
            nojurnaldok = result.aData[i]['noJournalDok'];
            $('#idskpd' + c).val(result.aData[i]['idskpd']);
            $('#ketskpd' + c).val(result.aData[i]['ketskpd']);
            $('#idbas' + c).val(result.aData[i]['idBas']);
            $('#kodeakun' + c).val(result.aData[i]['kodeakun']);
            $('#namaakun' + c).val(result.aData[i]['namaakun']);
            $('#debet' + c).val(accounting.formatNumber(result.aData[i]['nilaiDebet'], 2, '.', ","));
            $('#kredit' + c).val(accounting.formatNumber(result.aData[i]['nilaiKredit'], 2, '.', ","));

            if (result.aData[i]['nilaiDebet'] > 0){
                document.getElementById("debet" + c).readOnly = false;  //debet aktif
            } else{
                document.getElementById("kredit" + c).readOnly = false;  //kredit aktif
            }
            
            var keg = result.aData[i]['idKegiatan'];

            if (keg == null) {
                $('#idkegiatan' + c).val("");
                $('#kegiatan' + c).val("");
                $('#namakegiatan' + c).val("");
            } else {
                $('#idkegiatan' + c).val(result.aData[i]['idKegiatan']);
                $('#kegiatan' + c).val(result.aData[i]['kodeKeg']);
                $('#namakegiatan' + c).val(result.aData[i]['namaKeg']);
            }

            jenis = result.aData[i]['jenis'];
            beban = result.aData[i]['beban'];

            //document.getElementById("jenis"+c).value = jenis;

            setUlangJenis(jenis, c);

            setkriteria(jenis, c);

            if (beban == null) {
                beban = "";
            }

            document.getElementById("beban" + c).value = beban;
            // bootbox.alert("nilai jenis = "+ jenis +" || nilai beban = "+beban);

            td += result.aData[i]['nilaiDebet'];
            tk += result.aData[i]['nilaiKredit'];

        }

        $('#totaldebet').val(accounting.formatNumber(td, 2, '.', ","));
        $('#totalkredit').val(accounting.formatNumber(tk, 2, '.', ","));

        var table = document.getElementById('jourtablebody');
        var rows = table.rows;
        var jumrow = (rows.length) - banyakdataawal - 1;
        //bootbox.alert( "jumlah baris (data) == (rows.length) |"+ (rows.length) +"| - banyakdataawal |"+ banyakdataawal +"| -1 == " + jumrow );
        var jum;
        var kurang = 1 + jumrow;
        if (jumrow > 0) {
            for (i = 1; i <= jumrow; i++) {
                jum = rows.length;
                if (banyakdataawal > jumrow) { // banyakdataawal == jumlah data baru, jumrow == jumlah data lama
                    table.deleteRow(kurang);
                } else if (banyakdataawal <= jumrow) {
                    table.deleteRow(jum - 1);
                }
            }
        }
    });
}

function clearrow() {
    var i;
    var table = document.getElementById('jourtablebody');
    var rows = table.rows;
    var jum = rows.length;
    var kosong = "";
    statusupdate = 0;
    idrow = 0;

    for (i = 1; i < jum; i++) {
        table.deleteRow(1); // dihapus baris ke-1 sampai jumlahnya habis
    }

    $("#tglPosting").datepicker("setDate", new Date());
    $("#tglDok").datepicker("setDate", new Date());

    $('#totaldebet').val(0);
    $('#totalkredit').val(0);

    $('#noJournal').val(kosong);
    $('#ketJour').val(kosong);
    $('#noDok').val(kosong);

    document.getElementById("koreksidariBPK").checked = false;

    //$('#tglPosting').val(kosong);
    //$('#tglDok').val(kosong);

}

function hapusdata() {
    if (statusupdate == 1) {

        bootbox.confirm("Anda akan menghapus data Jurnal Umum SKPD dengan nomor " + $("#noJournal").val() + " tahun " + $("#tahunAngg").val() + ",  lanjutkan ? ", function(result) {
            if (result) {
                //bootbox.alert("Masuk hapus data");

                return   $.ajax({
                    type: "POST",
                    url: getbasepath() + "/journal/json/prosesupdateaktif",
                    contentType: "text/plain; charset=utf-8",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify({"tahun": $("#tahunAngg").val(), "nojur": $("#noJournal").val()})
                }).always(function(data) {
                    clearrow();
                    bootbox.alert(data.responseText);
                });

            } else {
                bootbox.hideAll();
                return result;
            }

        });

    } else if (statusupdate == 0) {
        bootbox.alert("Pilih Data Jurnal Terlebih Dulu");
    }
}

function setkoreksi(keterangan) {
    //console.log("keterangan koreksi = "+keterangan); 

    if (keterangan == 1) {
        document.getElementById("koreksidariBPK").checked = true;
    } else {
        document.getElementById("koreksidariBPK").checked = false;
    }

    getbanyakrinci();
}

function setComboJenis() {
    var id = idbuton;

    var kodeakun = $('#kodeakun' + id).val();
    var panjangkode = kodeakun.length;
    var kode, kode3, kode1;
    var optjenis;

    kode = kodeakun.substr(0, 5);
    kode3 = kodeakun.substr(0, 3);
    kode1 = kodeakun.substr(0, 1);

    if (kode == "5.1.1" || kode == "5.1.2" || kode == "5.1.8") {
        //console.log("kode jenis ---> BTL");
        optjenis = '<option value="1">BTL</option>';

    } else if (kode == "5.1.3" || kode == "5.1.4" || kode == "5.1.5" || kode == "5.1.6" || kode == "5.1.7") {
        //console.log("kode jenis ---> BTL BANTUAN");
        optjenis = '<option value="2">BTL BANTUAN</option>';

    } else if (kode3 == "5.2") {
        //console.log("kode jenis ---> BL");
        optjenis = '<option value="3">BL</option>';

    } else if (kode3 == "6.1") {
        //console.log("kode jenis ---> PENERIMAAN");
        optjenis = '<option value="5">PENERIMAAN</option>';

    } else if (kode3 == "6.2") {
        //console.log("kode jenis ---> BIAYA");
        optjenis = '<option value="4">BIAYA</option>';

    } else if (kode1 == "4") {
        //console.log("kode jenis ---> PENERIMAAN");
        optjenis = '<option value="5">PENERIMAAN</option>';

    } else {
        //console.log("kode jenis ---> LAIN-LAIN");
        optjenis = '<option value="9">LAIN-LAIN</option>';
    }

    $("#jenis" + id).html(optjenis);
    var jenis = $("#jenis" + id).val();
    //console.log("value jenis == "+jenis);
    setkriteria(jenis, id);

}

function tampilCek() {

    var i, nb, nd;
    var jumlist = 0;
    var banyakNR;

    banyakNR = idrow - banyakdataawal;

    for (i = 1; i <= banyakdataawal; i++) { // untuk hitung banyak debet kredit = 0, untuk update c_aktif
        var nb2, nd2;
        nb2 = parseFloat(accounting.unformat($('#debet' + i).val(), ","));//$("#debet"+i).val();
        nd2 = parseFloat(accounting.unformat($('#kredit' + i).val(), ","));//$("#kredit"+i).val();

        if ($('#debet' + i).val() == "") {
            nb2 = 0;
        }

        if ($('#kredit' + i).val() == "") {
            nd2 = 0;
        }

        if ((nb2 == 0) && (nd2 == 0)) { // kondisi debet kredit kosong
            jumlist += 1;
            bootbox.alert("id jour kosong = " + idjurnal[i]);
        }
    }
    //bootbox.alert("Jumlah list kosong = " + jumlist);

    /*
     var table = document.getElementById('jourtablebody');
     var rows = table.rows; 
     var jum = rows.length ;
     var kurang;
     idrow = 0;
     var jumrow = (rows.length)- banyakdataawal-1;
     
     bootbox.alert("data lama = "+ jumrow +" || data baru = "+banyakdataawal);
     
     kurang = 1+ jumrow;
     for (i = 1; i <= jumrow; i++) {
     jum = rows.length ;
     //bootbox.alert("Jumrow == "+jum);
     if (banyakdataawal > jumrow){ 
     table.deleteRow(kurang);
     } else if(banyakdataawal <= jumrow){
     table.deleteRow(jum-1);
     }  
     }
     */

}
