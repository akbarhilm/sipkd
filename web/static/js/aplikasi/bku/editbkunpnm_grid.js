$(document).ready(function() {
    
    document.getElementById("labelnippptk").style.display = "block";
    document.getElementById("labelnamapptk").style.display = "block";

    
    getbanyakrinci();
});

// global variable
var idrow = 0;
var banyakdata = 0;
var idbuton;
var akunnama = new Array();

function cektambah() {
    var kode = $("#kodeTransaksi").val();

    if (kode == "NP") {
        tambahRowNP();
    } else if (kode == "NM") {
        tambahRowNM();
    } 
}

function tambahRowNP() {

    idrow += 1;

    var table = document.getElementById('jourtablebody');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    var cell6 = row.insertCell(5);
    var cell7 = row.insertCell(6);

    //cell1.innerHTML = "<td class= 'center' >" + idrow + "</td>";
    cell1.innerHTML = "<td class= 'center' >" + idrow + " <input type='hidden' id='idBku" + idrow + "' name='idBku" + idrow + "' > </td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    cell3.innerHTML = "<td class='center'><textarea  id='namaakun" + idrow + "' name='namaakun" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:300px;' readonly='true' > </textarea> </td>";
    cell4.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku/listkegiatan?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell5.innerHTML = "<td class='center'><textarea  id='namakeg" + idrow + "' name='namakeg" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:350px;' readonly='true' > </textarea> </td>";
    cell6.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";
    cell7.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

    formatnumberonkeyup();

}

function tambahRowNM() {

    idrow += 1;

    var table = document.getElementById('jourtablebody');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    var cell6 = row.insertCell(5);
    var cell7 = row.insertCell(6);

    //cell1.innerHTML = "<td class= 'center' >" + idrow + "</td>";
    cell1.innerHTML = "<td class= 'center' >" + idrow + " <input type='hidden' id='idBku" + idrow + "' name='idBku" + idrow + "' > </td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    cell3.innerHTML = "<td class='center'><textarea  id='namaakun" + idrow + "' name='namaakun" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:300px;' readonly='true' > </textarea> </td>";
    cell4.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku/listkegiatan?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell5.innerHTML = "<td class='center'><textarea  id='namakeg" + idrow + "' name='namakeg" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:350px;' readonly='true' > </textarea> </td>";
    cell6.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney' value = '0' /> </td>";
    cell7.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";

    formatnumberonkeyup();

}

function getbutton(id) {
    idbuton = id;
}

function getKegiatan() {
    var id = idbuton;

    $('#idkeg' + id).val($('#idKegpop').val());
    $('#kegiatan' + id).val($('#kodeKegpop').val());
    $('#namakeg' + id).val($('#namaKegpop').val());

    setAkunCombo($('#idKegpop').val());
}

function setAkunCombo(id) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var jenistrans = $('#kodeTransaksi').val();

    $.getJSON(getbasepath() + "/bku/json/getAkunCombo", {tahun: tahun, idskpd: idskpd, idkegiatan: id, keterangan: "SPJ"},
    function(result) {
        var banyak, kode, ket;
        var opt = "";
        banyak = result.aData.length;

        if (jenistrans == "NP" || jenistrans == "NM") {
            opt = '<option value="" selected></option>';
        }

        try {
            if (banyak > 0) {
                for (var i = 0; i < banyak; i++) {
                    kode = result.aData[i]['idBas'];
                    ket = result.aData[i]['kodeakun'];

                    opt += '<option value="' + kode + '" >' + ket + '</option>';
                    akunnama[kode] = result.aData[i]['namaakun'];
                }
                $("#akun" + idbuton).html(opt);
                var cek = "akun" + idbuton;
                setNamaAkun(cek);
            }
        } catch (e) {
            console.log(e);
        }
    });
}

function setNamaAkun(textid) {
    var id = textid.substring(4);
    var idbas = $("#akun" + id).val();
    $("#namaakun" + id).val(akunnama[idbas]);
}

function getbanyakrinci() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var tglpost = $("#noJournalDok").val();
    var nobukti = $("#noBukti").val();
    var nobku = $("#noBKU").val();
    var ket = "NPD";

    $.getJSON(getbasepath() + "/bku/json/getBanyakEdit", {idskpd: idskpd, tahun: tahun, nobukti: nobukti, tglpos: tglpost, ket: ket, nobku:nobku},
    function(result) {
        banyakdata = result;

        $('#banyakrinci').val(result);

        getrincilist();
    });
}

function getrincilist() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var tglpost = $("#noJournalDok").val();
    var nobukti = $("#noBukti").val();
    var ket = "NPD";
    var nobku = $("#noBKU").val();
    var valbanyak = $('#banyakrinci').val();

    $.getJSON(getbasepath() + "/bku/json/valtabel", {idskpd: idskpd, tahun: tahun, nobukti: nobukti, tglpos: tglpost, ket: ket, nobku:nobku},
    function(result) {
        var i, c;
        var kode = $("#kodeTransaksi").val();


        for (i = 1; i <= valbanyak; i++) {
            if (kode == "NP") {
                tambahRowNP();
            } else if (kode == "NM") {
                tambahRowNM();
            } 
        }

        for (i = 0; i < valbanyak; i++) {
            c = i + 1;
            $('#idBku' + c).val(result.aData[i]['idBku']);
            $('#idkeg' + c).val(result.aData[i]['idKegiatan']);
            $('#kegiatan' + c).val(result.aData[i]['kodeKeg']);
            $('#namakeg' + c).val(result.aData[i]['namaKeg']);
            $('#nilaiMasuk' + c).val(accounting.formatNumber(result.aData[i]['nilaiMasuk'], 2, '.', ","));
            $('#nilaiKeluar' + c).val(accounting.formatNumber(result.aData[i]['nilaiKeluar'], 2, '.', ","));
            setAkunComboEdit(result.aData[i]['idKegiatan'], c, result.aData[i]['idBas']);
        }
    });
}

function setAkunComboEdit(id, idbaris, idbas) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var jenistrans = $('#kodeTransaksi').val();

    $.getJSON(getbasepath() + "/bku/json/getAkunCombo", {tahun: tahun, idskpd: idskpd, idkegiatan: id, keterangan: "SPJ"},
    function(result) {
        var banyak, kode, ket;
        var opt = "";
        banyak = result.aData.length;

        if (jenistrans == "NP" || jenistrans == "NM") {
            opt = '<option value="" selected></option>';
        }

        try {
            if (banyak > 0) {
                for (var i = 0; i < banyak; i++) {
                    kode = result.aData[i]['idBas'];
                    ket = result.aData[i]['kodeakun'];

                    opt += '<option value="' + kode + '" >' + ket + '</option>';
                    akunnama[kode] = result.aData[i]['namaakun'];

                }
                $("#akun" + idbaris).html(opt);
                var cek = "akun" + idbaris;
                $("#akun" + idbaris).val(idbas);
                setNamaAkunEdit(cek);
            }
        } catch (e) {
            console.log(e);
        }
    });
}

function setNamaAkunEdit(textid) {
    var id = textid.substring(4);
    var idbas = $("#akun" + id).val();
    $("#namaakun" + id).val(akunnama[idbas]);
}

function cekLengkap() {
    var table = document.getElementById('jourtablebody');
    var rows = table.rows;
    var jum = rows.length;
    var tglPost = $("#tglPosting").val();
    var nobukti = $("#noBukti").val();
    var tglDok = $("#tglDok").val();
    var datalengkap = true;
    var jenistrans = $("#kodeTransaksi").val();
    var filling = $("#inboxFile").val();

    if (jum > 0) {

        if (jenistrans == "NP" || jenistrans == "NM") {
            for (var a = 1; a <= idrow; a++) {
                if ($('#idkeg' + a).val() == "" || $('#nilaiMasuk' + a).val() == "" || $('#nilaiKeluar' + a).val() == "") {
                    datalengkap = false;
                }
            }
        } 

        if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || datalengkap == false) {
            bootbox.alert("Pengisian Data Harus Lengkap");
        } else {
            update();
        }
    }
}

function update() {

    var varurl = getbasepath() + "/bku/json/simpanupdatenpd";
    var dataac = [];
    var nilainr = [];
    var nilailist = [];
    var i;
    var banyakNR;
    var jumNR = 0;
    var nilainpd = 0;
    var jenistrans = $('#kodeTransaksi').val();
    var fileinbox = $('#inboxFile').val();
    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal;
    
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;

    banyakNR = idrow - banyakdata; // menghitung jumlah row baru (insert)

    for (i = 0; i < banyakdata; i++) { // list update
        var id2 = i + 1;
        var idbas;

        if ($("#akun" + id2).val() == null) {
            idbas = "";
        } else {
            idbas = $("#akun" + id2).val();
        }
        
        console.log("jenistrans = "+jenistrans);
        
        if(jenistrans == "NP"){
            nilainpd = nilainpd + parseFloat(accounting.unformat($('#nilaiKeluar' + id2).val(), ","));
            
        } else if(jenistrans == "NM") {
            nilainpd = nilainpd + parseFloat(accounting.unformat($('#nilaiMasuk' + id2).val(), ","));
        }

        var pararr2 = {
            idBku: $("#idBku" + id2).val(),
            nilaimasuk: parseFloat(accounting.unformat($('#nilaiMasuk' + id2).val(), ",")),
            nilaikeluar: parseFloat(accounting.unformat($('#nilaiKeluar' + id2).val(), ",")),
            idbas: idbas,
            kodekegiatan: $("#kegiatan" + id2).val(),
            uraianbukti: $("#namaakun" + id2).val(),
            idkegiatan: $("#idkeg" + id2).val(),
            kodeakun: $('select[name="akun' + id2 + '"]').find(":selected").text()
        };
        nilailist[i] = pararr2;
    }

    for (i = 0; i < banyakNR; i++) { // list insert
        var id = banyakdata + i + 1;
        
        if(jenistrans == "NP"){
            nilainpd = nilainpd + parseFloat(accounting.unformat($('#nilaiKeluar' + id).val(), ","));
        } else if(jenistrans == "NM") {
            nilainpd = nilainpd + parseFloat(accounting.unformat($('#nilaiMasuk' + id).val(), ","));
        }
        
        var pararr = {
            nilaimasuk: parseFloat(accounting.unformat($('#nilaiMasuk' + id).val(), ",")),
            nilaikeluar: parseFloat(accounting.unformat($('#nilaiKeluar' + id).val(), ",")),
            idbas: $("#akun" + id).val(),
            kodekegiatan: $("#kegiatan" + id).val(),
            uraianbukti: $("#namaakun" + id).val(),
            idkegiatan: $("#idkeg" + id).val(),
            kodeakun: $('select[name="akun' + id + '"]').find(":selected").text()
        };
        nilainr[jumNR] = pararr;
        jumNR += 1; // untuk index nilainr[]
    }
    
    console.log("banyak data == "+banyakdata);
    console.log("jum data baru == "+jumNR);
    console.log("nilainpd == "+ nilainpd);

    var datajour = {
        tahun: $("#tahun").val(),
        idskpd: $("#idskpd").val(),
        tglposting: tanggal,
        kodetransaksi: $('#kodeTransaksi').val(),
        nobukti: $("#noBukti").val(),
        tgldok: $("#tglDok").val(),
        jenis: "3",
        beban: $("#beban").val(),
        jumNR: jumNR,
        fileinbox: fileinbox,
        namapptk: $("#namaPptk").val(),
        nippptk: $("#nipPptk").val(),
        uraian: "edit",
        noBKU: $('#noBKU').val(),
        nilainpd : nilainpd,
        nilailist: nilailist,
        nilainr: nilainr
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
        getbanyakrinci();
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
    });
}

function clearrow() {
    var i;
    var table = document.getElementById('jourtablebody');
    var rows = table.rows;
    var jum = rows.length;

    idrow = 0;

    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-1 sampai jumlahnya habis
    }
}
