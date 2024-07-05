
$(document).ready(function() {
    is();
    gridbapkasrinci();
    hitungnilaiBapKas();
    //countRow();
});

var idrow = 0;
var idrowbaru = 0;
var banyaktampil = 0;
var noNR = 0;
var banyakdata;
var kosong = 0;
var banyakdataawal;
var banyakrinciawal = 0;

var nomorgrid = 0;

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
        $("#blnBkuBa").html(opt);
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

    $("#nilaiUangTotalBkuBa1").val(accounting.formatNumber(total, 2, '.', ","));

    $("#nilaiUangTotalBkuBa1").val(accounting.formatNumber(total, 2, '.', ","));
    /*$("#nilaiUangKertas1").val(accounting.formatNumber(kr, 2, '.', ","));
     $("##nilaiUangLogam1").val(accounting.formatNumber(logam, 2, '.', ","));
     $("#nilaiUangSp2d1").val(accounting.formatNumber(sp2d, 2, '.', ","));
     $("#nilaiUangSaldoBank1").val(accounting.formatNumber(saldoBank, 2, '.', ","));
     $("#nilaiUangSuratBerharga1").val(accounting.formatNumber(sb, 2, '.', ","));*/

}

function gridbapkasrinci() { // untuk nampilin grid awal
    $("#btnSimpan").hide();
    var urljson = getbasepath() + "/bapkas/json/getlistbapkasrinci";
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
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
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

                try {
                    var BapKastotal = 0;

                    for (var i = iStart; i < iEnd; i++) {
                        BapKastotal += parseFloat(aaData[i]['nilaiBapKas']);
                        //console.log(BapKastotal + " = " + aaData[i]['nilaiBapKas'])
                    }
                    $("#totalBapKas").val(accounting.formatNumber(BapKastotal, 2, '.', ","));
                    $("#totalbk").val(accounting.formatNumber(BapKastotal, 2, '.', ","));

                } catch (e) {
                    console.log(e);
                }
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) { //format untuk tampilan body tabel
                var numStart = this.fnPagingInfo().iStart;
                index = numStart + iDisplayIndexFull + 1;
                var idSkpdBAPKas = "<input type='hidden' id='idSkpdBAPKas" + index + "'   name='idSkpdBAPKas" + index + "'  value='" + aData['idSkpdBAPKas'] + "'  />";

                var idSkpdBAPKasRinci = "<input type='hidden' id='idSkpdBAPKasRinci" + index + "' name='idSkpdBAPKasRinci" + index + "' value='" + aData['idSkpdBAPKasRinci'] + "' />";
                var namaBapKas = "<input type='text' name='namaBapKas" + index + "' id='namaBapKas" + index + "' value = '" + aData['namaBapKas'] + "'  readonly='true'  />";
                //var nilaiBapKas = "<input type='text' name='nilaiBapKas" + index + "' id='nilaiBapKas" + index + "' class='inputmoney' value = '" + aData['nilaiBapKas'] + "'  readonly='true' onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaiBapKas()'  />";
                var pilih = " <a class='icon-remove' href='#' onclick='hapusrinci(" + index + ")' ></a>";
                var isceked = aData['nilaiBapKas'] >= 0 ? 'checked' : '';
                //var isceked = aData['nilaiBapKas'] !== 0 ? 'checked' : '';
                var readonly = 'readonly';
                var inputcek;
                if (isceked === 'checked') {
                    readonly = '';
                }
                //inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";

                var inputBapKas = "<input type='text'   id='nilaiBapKas" + index + "'    name='nilaiBapKas" + index + "'   value='" + aData['nilaiBapKas'] + "'  class='inputmoney '   onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaiBapKas()'  readonly='true'   />";

                var nilaiBapKashidden = "<input type='hidden' name='nilaiBapKasorg" + index + "'     id='nilaiBapKasorg" + index + "' value='" + aData['nilaiBapKas'] + "' />";
                var nilaiBapKas = parseFloat(aData['nilaiBapKas']) > 0 ? aData['nilaiBapKas'] : $("#nilaiUangSaldoBkuBa").val();

                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + " ' name='cekpilih" + index + "' onchange='enablerow(this," + index + ")'  />";
                var penanda = "<input type='hidden' id='penanda" + index + "' name='penanda" + index + " ' value ='" + 0 + "'/>";
                //var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idSkpdBAPKasRinci'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";


                idrow = index;
                if (index === null || index === "" || index === 0) {
                    nomorgrid = 0;
                    idrowawal = 0;
                }
                else {
                    nomorgrid = parseInt(aData['idSkpdBAPKasRinci']);
                    idrowawal = idrow;
                }
                $('td:eq(0)', nRow).html(index + idSkpdBAPKasRinci + idSkpdBAPKas);
                $('td:eq(1)', nRow).html(namaBapKas);
                $('td:eq(2)', nRow).html(inputBapKas + nilaiBapKashidden);
                //$('td:eq(3)', nRow).html(pilih + idSkpdBAPKasRinci);
                $('td:eq(3)', nRow).html(inputcek);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSkpdBAPKasRinci", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaBapKas", "bSortable": true, sClass: "left"},
                {"mDataProp": "nilaiBapKas", "bSortable": true, sClass: "center"},
                // {"mDataProp": "idSkpdBAPKasRinci", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSkpdBAPKasRinci", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    getbanyakrincibapkas();
}

function getTotal(id) { //untuk menghitung nilai total ketika menambahkan data baru 
    var i, totalBapKas, nilaiBapKas, totalBap;
    totalBapKas = 0;
    nilaiBapKas = 0;
    totalBap = 0;

    for (i = 1; i <= id; i++) {
        if ($('#nilaiBapKas' + i).val() == "") {
            totalBapKas = 0;
        } else {
            totalBapKas = parseFloat(accounting.unformat($('#nilaiBapKas' + i).val(), ","));
        }
        totalBap += totalBapKas;
    }

    $('#totalbk').val(accounting.formatNumber(totalBap, 2, '.', ","));
}

function enablerow(obj, idSkpdBAPKasRinci) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
        $("#penanda" + idSkpdBAPKasRinci).val("1");
    } else {
        $("#penanda" + idSkpdBAPKasRinci).val("0");
    }
    setdisabled(param, idSkpdBAPKasRinci);
}
function setdisabled2(param, idSkpdBAPKasRinci) {
    $("#nilaiBapKas" + idSkpdBAPKasRinci).attr("readonly", param);
    $("#namaBapKas" + idSkpdBAPKasRinci).attr("readonly", param);
}
function setdisabled(param, idSkpdBAPKasRinci) {
    $("#nilaiBapKas" + idSkpdBAPKasRinci).attr("readonly", param);
    $("#namaBapKas" + idSkpdBAPKasRinci).attr("readonly", param);

    hitungnilaiBapKas();
}

function hapusrinci(id) {
    bootbox.confirm("Apakah Anda Yakin Akan Menghapus data Rinci ini ?", function(result) {
        if (result) {
            //hapusrow(id);
            var table = document.getElementById('bapkasrincitablebody');
            table.deleteRow(id);
            bootbox.alert("Data Rinci Berhasil Dihapus");

        } else {
            bootbox.hideAll();
            return result;
        }
    });
}

function hapusrow2(id) {
    var table = document.getElementById('bapkasrincitablebody');
    var selisih = idrow - jumhapus;
    var rowhapus = id - jumhapus;

    if (id > selisih) { // jika id yang akan dihapus lebih besar dari jumlah row yang ada 
        //console.log("table.deleteRow(rowhapus) = " + rowhapus);
        table.deleteRow(rowhapus);
    } else {
        //console.log("table.deleteRow(id) = " + id);
        table.deleteRow(id);
    }

    jumhapus += 1;

    getTotal();

}

function tambahRow() {
    idrow += 1;
    nomorgrid += 1;
    idrowbaru += 1;

    var rowCount = document.getElementById('bapkasrincitablebody').rows.length;
    var table = document.getElementById('bapkasrincitablefoot2');

    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    cell1.innerHTML = "<td class= 'center'  >" + idrow + " </td>";
    cell2.innerHTML = "<td> <input type='text' name='namaBapKas" + idrow + "' id='namaBapKas" + idrow + "' /> </td>";
    cell3.innerHTML = "<td> <input type='text' name='nilaiBapKas" + idrow + "' id='nilaiBapKas" + idrow + "'  class='inputmoney'  value = '0' onkeyup='getTotal(" + idrow + "),cektotalbk(" + idrow + ")'  /> </td>";
    //cell4.innerHTML = "<td class= 'center'> <a class='icon-edit' href='#'   ></a> - <a class='icon-remove' href='#' ></a> </td>";
    //cell4.innerHTML = "<td class= 'center' ><a class='center icon-remove' onclick='hapusBapKasRinci(" + idrow + "); getbutton(" + idrow + ")' ></a> </td>";
    cell4.innerHTML = "<td class= 'center' ><a class='center icon-remove' onclick='hapusBapKasRinci(" + idrow + ")' ></a> </td>";

    formatnumberonkeyup();
    $('#banyakrinci').val(idrow);
    $('#idrowbaru').val(idrowbaru);
    //idbas[idrow] = '';
}

function countRow() {
    var rowCount = document.getElementById('bapkasrincitablebody').rows.length;
    var a, idrow, countidrow, banyakrinci;
    for (var a = 1; a <= idrow; a++) {
        countidrow += 1;
        banyakrinci += countidrow;
    }
}

function getbanyakrincibapkas( ) {
    $.getJSON(getbasepath() + "/bapkas/json/getbanyakrincibapkas", {idSkpdBAPKas: $("#idSkpdBAPKas").val()},
    function(result) {
        $('#banyakrinci').val(result);
        banyakrinciawal = result;
    });
}

//cek validasi jumlah rinci harus <=nilaiUangSaldoBkuBa
function validasinilai(idbl) {
    var nilaispp = accounting.unformat($("#nilaispp" + idbl).val(), ",");
    var nilaisisa = accounting.unformat($("#nilaiSppSisa" + idbl).val(), ",");
    var status = nilaispp >= nilaisisa ? false : true;

    if (!status) {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari nilai SPP Sisa", function() {
            //console.log("nilaisisa = " + nilaisisa);
            $('#nilaispp' + idbl).autoNumeric('set', nilaisisa);// edit 9 Nov 2016 by zainab : format input money diganti
            //$("#nilaispp" + idbl).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
            $("#nilaispp" + idbl).focus();
            hitungnilaispp();
        });
    } else {
        return true;
    }
}

function hitungnilaispp() {
    var searchIDs = $("#spprincitable input:checkbox:checked").not("#pilihall").map(function() {
        return $(this).val();
    }).get();
    var total = 0;
    $.each(searchIDs, function(idx, data) {
        total += parseFloat(accounting.unformat($("#nilaispp" + data).val(), ","));
    })
    $("#totalspp").val(accounting.formatNumber(total, 2, '.', ","));
}

function getNilaiTotalSelisihBAPKas1() {
    var kertas, logam, sp2d, sb, saldoBank, total, kr, tbku;
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
    if ($('#nilaiUangTotalBkuBa1').val() == "") {
        tbku = 0;
    } else {
        tbku = parseFloat(accounting.unformat($('#nilaiUangTotalBkuBa1').val(), ","));
    }

    total = (kertas + logam + sp2d + saldoBank + sb) - tbku;

    $("#nilaiUangSelisihBkuBa").val(accounting.formatNumber(total, 2, '.', ","));
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
    $('#nilaiUangSelisihBkuBa').val(accounting.formatNumber(totalselisih, 2, '.', ","));
}
 

function cektotalbk(id) {
    var totalbk1 = accounting.unformat($("#totalbk").val(), ","); 
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

function pasangvalidatebesarperfield(idSkpdBAPKasRinci) {
    var nilaiUangSaldoBkuBa1 = accounting.unformat($("#nilaiUangSelisihBkuBa").val(), ",");// $("nilaiUangSaldoBkuBa").val();
    var totalBapKas = accounting.unformat($("#totalbk").val(), ",");
    var totalBap = 0;
    
    var i, totalBapKas;
    totalBapKas = 0;
    
    for (i = 1; i <= idrow; i++) {
        if ($('#nilaiBapKas' + i).val() == "") {
            totalBapKas = 0;
        } else {
            totalBapKas = parseFloat(accounting.unformat($('#nilaiBapKas' + i).val(), ","));
        }
        totalBap += totalBapKas;
    }
    console.log("inap cek totalBap = "+totalBap);
    console.log("inap cek nilaiUangSaldoBkuBa1 = "+nilaiUangSaldoBkuBa1);
    
    var status = totalBap <= nilaiUangSaldoBkuBa1;
    //console.log(" cek anita pasangvalidatebesarperfield=>", (totalBapKas < nilaiUangSaldoBkuBa) + "  " + status + "  " + nilaiBapKas + " " + nilaiUangSaldoBkuBa1);
    if (!status) {
        bootbox.alert("Nilai Total rinci Harus Sama dengan Nilai Selisih BAP Kas", function() {
            $("#nilaiBapKas" + idSkpdBAPKasRinci).autoNumeric('set', 0);
            //$("#nilaiBapKas" + idSkpdBAPKasRinci).focus();
            hitungnilaiBapKas();
        });

    } else {
        return true;
    }
}

function hitungnilaiBapKas2() {
    console.log("masuk fungsi hitungnilaiBapKas");
    var total = 0;
    $(".nilaiBapKas").each(function(index, item) {
        total += parseFloat($(item).autoNumeric('get'));
    });
    $("#totalbk").val(accounting.formatNumber(total, 2, '.', ","));
}

function hitungnilaiBapKas() {
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

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function setformatKertas(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaiUangKertas1').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}
function setformatLogam(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaiUangLogam1').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}
function setformatSp2d(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaiUangSp2d1').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}
function setformatSaldoBank(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaiUangSaldoBank1').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}
function setformatSuratBerharga(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaiUangSuratBerharga1').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}
function setformatSaldoBkuBa(nilai) {
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#nilaiUangSaldoBkuBa').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
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
    //table.deleteRow(1);
    
    //console.log("banyakrinciawal) = " + banyakrinciawal);
    var id2 = id-idrowbaru+1-jumhapus;
    
    table.deleteRow(id2);
    //hapusrow(id2);
    
    jumhapus += 1;

}

function hapusrow(id) {
    var table = document.getElementById('bapkasrincitablefoot2');
    var selisih = idrow - jumhapus;
    var rowhapus = id - jumhapus;
    
    if (id > selisih) { // jika id yang akan dihapus lebih besar dari jumlah row yang ada 
        //console.log("table.deleteRow(rowhapus) = " + rowhapus);
        table.deleteRow(rowhapus);
    } else {
        //console.log(selisih + " =table.deleteRow(id) = " + id);
        table.deleteRow(id);
    }

    jumhapus += 1;

    getTotal();

}
