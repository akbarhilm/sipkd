
$(document).ready(function() {
    is();
    //gridbapkasrinci();
    countRow();
    getNilaiTotalBAPKas(); // ditambah agar jika nilainya 0 tidak error jika langsung disimpan (Nilai Total BAP Kas tidak null)
});

var idrow = 0;
var banyaktampil = 0;
var noNR = 0;
var nomorgrid = 0;
var banyakdata;
var kosong = 0;
var totaldebetNR;
var totalkreditNR;
var totaldebet;
var totalkredit;
var grandtotalD = 0;
var grandtotalK = 0;
var idbas = new Array();
var idbaslist = new Array();
var idpilih;
var unitawal;

var banyakdataawal;


var jumhapus = 0;
var idbuton;

function pindahhalamanadepan() {
    window.location.replace(getbasepath() + "/bapkas/indexbapkas");
}

function setBulan(tahun, idskpd) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val(); 

    $.getJSON(getbasepath() + "/bapkas/json/setBulan", {tahun: tahun, idskpd: idskpd},
    function(result) { 

        var banyak, bulan, ketbulan;
        var opt = "";

        banyak = result.aData.length;

        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                bulan = result.aData[i]['BLNBKUBA'];
                ketbulan = result.aData[i]['KETBUL'];
                //alert(bulan+ketbulan);
                opt += '<option value="' + bulan + '">' + ketbulan + '</option>';
            }
        }
        $("#blnBkuBa").html(opt); 
        getNilaiKas();
    });

}

function setBulanEdit(tahun, idskpd) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();


    $.getJSON(getbasepath() + "/bapkas/json/setBulanEdit", {tahun: tahun, idskpd: idskpd},
    function(result) {
        //console.log(result);

        var banyak, bulan, ketbulan;
        var opt = "";

        banyak = result.aData.length;

        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                bulan = result.aData[i]['BLNBKUBA'];
                ketbulan = result.aData[i]['KETBUL'];
                //alert(bulan+ketbulan);
                opt += '<option value="' + bulan + '">' + ketbulan + '</option>';

            }
        }

        $("#blnBkuBa1").html(opt);
        getNilaiKas();

    });

}

function getNilaiTotalBAPKas() {
    var kertas, logam, sp2d, sb, saldoBank, total, kr;
    total = 0;
    if ($('#nilaiUangKertas1').val() == "") {
        kertas = 0;
    } else {
        kertas = parseFloat(accounting.unformat($('#nilaiUangKertas1').val(), ","));//(accounting.unformat($('#nilaiUangKertas1').val(), ","));

    }

    if ($('#nilaiUangLogam1').val() == "") {
        logam = 0;
    } else {
        logam = parseFloat(accounting.unformat($('#nilaiUangLogam1').val(), ","));
    }

    if ($('#nilaiUangSp2d1').val() == "") {
        sp2d = 0;
    } else {
        sp2d = parseFloat(accounting.unformat($('#nilaiUangSp2d1').val(), ","));
    }
    if ($('#nilaiUangSaldoBank1').val() == "") {
        saldoBank = 0;
    } else {
        saldoBank = parseFloat(accounting.unformat($('#nilaiUangSaldoBank1').val(), ","));
    }
    if ($('#nilaiUangSuratBerharga1').val() == "") {
        sb = 0;
    } else {
        sb = parseFloat(accounting.unformat($('#nilaiUangSuratBerharga1').val(), ","));
    }

    total = kertas + logam + sp2d + saldoBank + sb;

    //accounting.formatNumber(nilaiAnggaranSPDCurrent, 2, '.', ",")
    $("#nilaiUangTotalBkuBa1").val(accounting.formatNumber(total, 2, '.', ","));

    $("#nilaiUangTotalBkuBa1").val(accounting.formatNumber(total, 2, '.', ","));
    /*$("#nilaiUangKertas1").val(accounting.formatNumber(kr, 2, '.', ","));
     $("##nilaiUangLogam1").val(accounting.formatNumber(logam, 2, '.', ","));
     $("#nilaiUangSp2d1").val(accounting.formatNumber(sp2d, 2, '.', ","));
     $("#nilaiUangSaldoBank1").val(accounting.formatNumber(saldoBank, 2, '.', ","));
     $("#nilaiUangSuratBerharga1").val(accounting.formatNumber(sb, 2, '.', ","));*/

}

function gridbapkasrinci() { // untuk nampilin grid awal
    //document.getElementById("btnSimpan").style.visibility = "hidden";
    //console.log("CUMA TEST grid skpd nihil");
    $("#btnSimpan").hide();
    var urljson = getbasepath() + "/bapkas/json/getlistbapkasrinci";
    //console.log("idskpdval == "+$('#idskpd').val());
    //jumbarisawal=0;
    var index = 0;

    $("#bapkasrincitable").show();
    $("#bapkasrincitablefoot2").empty();
    if (typeof myTable == 'undefined') {
        myTable = $('#bapkasrincitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 10000, // buat nampilin semmua tanpa page
            //"sDom": '<"top"i>rt<"bottom"flp><"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idSkpdBAPKas", value: $('#idSkpdBAPKas').val()} // parsing parameter ke action "/lampasetttp/json/getlistasetttp"
                );
            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) { //nampilin table footer total
                var totalba = 0;
                for (var i = iStart; i < iEnd; i++) {
                }
                $("#totalnilaiba").val(accounting.formatNumber(totalba, 2, '.', ","));

            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) { //format untuk tampilan body tabel
                var numStart = this.fnPagingInfo().iStart;
                index = numStart + iDisplayIndexFull + 1;
                var idSkpdBAPKas = "<input type='hidden' id='idSkpdBAPKas" + index + "'   name='idSkpdBAPKas" + index + "'  value='" + aData['idSkpdBAPKas'] + "'  />";

                var idSkpdBAPKasRinci = "<input type='hidden' id='idSkpdBAPKasRinci" + index + "' name='idSkpdBAPKasRinci" + index + "' value='" + aData['idSkpdBAPKasRinci'] + "' />";
                //var namaBapKas = "<textarea type='text' id='namaBapKas" + index + "' name='namaBapKas" + index + "' value='" + aData['namaBapKas'] + "' readonly='true' style='margin: 0px; width: 200px; height: 42px;' ></textarea>";
                //var namaBapKas = "<input type='text' name='namaBapKas" + index + "' id='namaBapKas" + index + "' value = '" + aData['namaBapKas'] + "'  readonly='true' />";
                var namaBapKas = "<input type='text' name='namaBapKas'  style='border:none;margin:0;width:99%;'  kode='namaBapKas' value='" + aData['namaBapKas'] + "' />"

                var nilaiBapKas = "<input type='text' name='nilaiBapKas" + index + "' id='nilaiBapKas" + index + "' class='inputmoney' value = '" + aData['nilaiBapKas'] + "'  readonly='true' />";
                var pilih = "<a class='icon-edit' href='" + getbasepath() + "/bapkas/updatebapkas/" + aData['idBapKas'] + "'  ></a> - <a class='icon-remove' href='#' onclick='hapusdata(" + index + ")' ></a>";

                var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBtl'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";

                idrow = index;
                if (index == null || index == "" || index == 0) {
                    nomorgrid = 0;
                    idrowawal = 0;
                }
                else {
                    nomorgrid = parseInt(aData['idSkpdBAPKasRinci']);
                    idrowawal = idrow;
                }
                $('td:eq(0)', nRow).html(index + idSkpdBAPKasRinci + idSkpdBAPKas);
                $('td:eq(1)', nRow).html(namaBapKas);
                $('td:eq(2)', nRow).html(nilaiBapKas);
                $('td:eq(3)', nRow).html(pilih);

                return nRow;
            },
            "aoColumns": [//kolom nya sesuaikan nama di model
                {"mDataProp": "idSkpdBAPKasRinci", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaBapKas", "bSortable": true, sClass: "left"},
                {"mDataProp": "nilaiBapKas", "bSortable": true, sClass: "center"},
                {"mDataProp": "idSkpdBAPKasRinci", "bSortable": false, sClass: "center"}
            ]
        });
        //console.log("sesese "+idrow);
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function getTotal() {
    //untuk menghitung nilai total ketika menambahkan data baru 
    var i, totalBapKas, nilaiBapKas, totalBap;
    totalBapKas = 0;
    nilaiBapKas = 0;
    totalBap = 0;

    for (i = 1; i <= idrow; i++) {
        if ($('#nilaiBapKas' + i).val() == "") {
            totalBapKas = 0;
        } else {
            totalBapKas = parseFloat(accounting.unformat($('#nilaiBapKas' + i).val(), ","));
        }
        totalBap += totalBapKas;
    }
    $('#totalbk').val(accounting.formatNumber(totalBap, 2, '.', ","));
}

function tambahRow() {
    idrow += 1;
    nomorgrid += 1;

    var table = document.getElementById('bapkasrincitablefoot2');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    cell1.innerHTML = "<td class= 'center'  >" + idrow + " </td>";
    cell2.innerHTML = "<td> <input type='text' name='namaBapKas" + idrow + "' id='namaBapKas" + idrow + "' style='width: 900px;' /> </td>";
    cell3.innerHTML = "<td> <input type='text' name='nilaiBapKas" + idrow + "' id='nilaiBapKas" + idrow + "'  class='inputmoney'  value = '0' onkeyup='getTotal(),cektotalbk(" + idrow + ")'  /> </td>";
    cell4.innerHTML = "<td class= 'center' ><a class='center icon-remove' onclick='hapusBapKasRinci(" + idrow + "); getbutton(" + idrow + ")' ></a> </td>";

    formatnumberonkeyup();
    $('#banyakrinci').val(idrow);
    //idbas[idrow] = '';
}

function hapusBapKasRinci(id) {
    /*bootbox.confirm("Apakah Anda Yakin Akan Menghapus Komponen " + $("#namakomp" + id).val() + " ?", function(result) {
     if (result) {
     hapusrow(id);
     bootbox.alert("Data Komponen Berhasil Dihapus");
     
     } else {
     bootbox.hideAll();
     return result;
     }
     });
     */
    var table = document.getElementById('bapkasrincitablefoot2');
    
    console.log("id = " + id);
    console.log("idrow = " + idrow);
    console.log("jumhapus = " + jumhapus);
    //console.log(" = " + id);
    
    var id2 = id - idrow + 1 - jumhapus;

    table.deleteRow(id2);
    //hapusrow(id2);

    jumhapus += 1;
    //idrow -= 1;
    
    var banyak = $('#banyakrinci').val();
    banyak -= 1;
    $('#banyakrinci').val(banyak);
    

    //console.log("hapusBapKasRinci(id) = " + id);
    //hapusrow(id);
}

function hapusrow(id) {
    var table = document.getElementById('bapkasrincitablefoot2');
    var selisih = idrow - jumhapus;
    var rowhapus = id - jumhapus;

    if (id > selisih) { // jika id yang akan dihapus lebih besar dari jumlah row yang ada 
        console.log("table.deleteRow(rowhapus) = " + rowhapus);
        table.deleteRow(rowhapus);
    } else {
        console.log(selisih + " =table.deleteRow(id) = " + id);
        table.deleteRow(id);
    }

    jumhapus += 1;

    getTotal();

}

function getbutton(id) {
    idbuton = id;
}

function countRow() {
    var rowCount = document.getElementById('bapkasrincitablebody').rows.length;
    var a, idrow, countidrow, banyakrinci;
    for (var a = 1; a <= idrow; a++) {
        countidrow += 1;
        console.log("cek count idrow = " + idrow);
        banyakrinci += countidrow;
    }
    console.log("total banyakrinci == " + banyakrinci);
    console.log("total rowCount == " + rowCount);
    //$('#banyakrinci').val(banyakrinci);

}

function cekLengkap() {
    var pagu = accounting.unformat($("#sisabl").val(), ",");
    var totalrinci = accounting.unformat($("#totalrinciakun").val(), ",");
    var datalengkap = true;
    var table = document.getElementById('jourtablebody');
    var rows = table.rows;
    var jum = rows.length;

    if (jum > 0) {
        for (var a = 1; a <= idrow; a++) {
            console.log("volume = " + $('#volume' + a).val());
            if ($('#idkomp' + a).val() == "" || $('#volume' + a).val() <= 0) {
                datalengkap = false;
            }
        }

        if (datalengkap == false) {
            bootbox.alert("Pengisian Data Harus Lengkap / Volume Tidak Boleh Minus");
        } else if (totalrinci <= 0) {
            bootbox.alert("Data Tidak Boleh Kosong !");
        } else if (totalrinci > pagu) {
            bootbox.alert("Total Rincian tidak boleh lebih besar dari Sisa Pagu Kegiatan.");
        } else {
            //simpan();
            bootbox.alert("lengkap");
        }
    }

}

function getNilaiTotalSelisihBAPKas() {
    var kertas1, logam1, sp2d1, sb1, saldoBank1, total1, kr1, tbku1, totalselisih;
    totalselisih = 0;
    if ($('#nilaiUangKertas1').val() == "") {
        kertas1 = 0;
    } else {
        kertas1 = parseFloat(accounting.unformat($('#nilaiUangKertas1').val(), ","));//(accounting.unformat($('#nilaiUangKertas1').val(), ","));
    }

    if ($('#nilaiUangLogam1').val() == "") {
        logam1 = 0;
    } else {
        logam1 = parseFloat(accounting.unformat($('#nilaiUangLogam1').val(), ","));
    }

    if ($('#nilaiUangSp2d1').val() == "") {
        sp2d1 = 0;
    } else {
        sp2d1 = parseFloat(accounting.unformat($('#nilaiUangSp2d1').val(), ","));
    }
    if ($('#nilaiUangSaldoBank1').val() == "") {
        saldoBank1 = 0;
    } else {
        saldoBank1 = parseFloat(accounting.unformat($('#nilaiUangSaldoBank1').val(), ","));
    }
    if ($('#nilaiUangSuratBerharga1').val() == "") {
        sb1 = 0;
    } else {
        sb1 = parseFloat(accounting.unformat($('#nilaiUangSuratBerharga1').val(), ","));
    }
    if ($('#nilaiUangSaldoBkuBa').val() == "") {
        tbku1 = 0;
    } else {
        tbku1 = parseFloat(accounting.unformat($('#nilaiUangSaldoBkuBa').val(), ","));
    }

    totalselisih = (kertas1 + logam1 + sp2d1 + saldoBank1 + sb1) - tbku1;

    //$("#nilaiUangSelisihBkuBa").val(accounting.formatNumber(total, 2, '.', ","));
    //$('#nilaiUangSelisihBkuBa').val(totalselisih);

    $("#nilaiUangSelisihBkuBa").val(accounting.formatNumber(totalselisih, 2, '.', ","));

    $("#nilaiUangSelisihBkuBa").val(accounting.formatNumber(totalselisih, 2, '.', ","));
}

function cektotalbk(id) {
    var totalbk1 = accounting.unformat($("#totalbk").val(), ",");
    //meriksa nilai bk minus atau tidak
    if (totalbk1 < 0){ //maka minus
        totalbk1a = 0-totalbk1;
    }
    else {
        totalbk1a =totalbk1;
    } 
    var nilaiUangSaldoBkuBa1 = accounting.unformat($("#nilaiUangSelisihBkuBa").val(), ",");
    if (nilaiUangSaldoBkuBa1 < 0){ //maka minus
        nilaiUangSaldoBkuBa1a = 0-nilaiUangSaldoBkuBa1;
    }
    else {
        nilaiUangSaldoBkuBa1a =nilaiUangSaldoBkuBa1;
    } 
    var totalBap = 0;
    var n = id; 
    if (totalbk1a > nilaiUangSaldoBkuBa1a) { //jika total rinci > total bapkas  
        
        bootbox.alert("Nilai Total rinci Harus Sama dengan Nilai Selisih BAP Kas");
        $('#nilaiBapKas' + n).autoNumeric('set', 0); 
        getTotal();
    }
    else {
        //console.log("Nilai total rinci < total bapkas");
    }
}


function numbersonly(myfield, e, dec) {
    var key;
    var keychar;

    if (window.event)
        key = window.event.keyCode;
    else if (e)
        key = e.which;
    else
        return true;
    keychar = String.fromCharCode(key);

// control keys
    if ((key == null) || (key == 0) || (key == 8) ||
            (key == 9) || (key == 13) || (key == 27) || (key == 46) || (key == 45))
        return true;

// numbers
    else if ((("0123456789").indexOf(keychar) > -1))
        return true;

// decimal point jump
    else if (dec && (keychar == "."))
    {
        myfield.form.elements[dec].focus();
        return false;
    }
    else
        return false;
}

function getNilaiKas(){
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var bulan = $('#bulan').val();
    var blnBkuBa = $('#blnBkuBa').val();
    
    console.log("cek bulan="+bulan);
    console.log("cek blnBkuBa="+blnBkuBa);

    $.getJSON(getbasepath() + "/bapkas/json/getNilaiKas", {tahun: tahun, idskpd: idskpd, bulan: blnBkuBa},
    function (result) {

        var nilai = result.aData['nilaiUangSaldoBkuBa'];
         $("#nilaiUangSaldoBkuBa").val(accounting.formatNumber(nilai, 2, '.', ",")); 
    });

}