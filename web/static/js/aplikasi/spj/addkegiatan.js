$(document).ready(function () {
    //getkodeaktif();
    validasisisaspj();
    $('#kegiatantable').hide();
    var kode = $('#kodeBeban').val();
    if (kode == "UP") {
        $('#Beban').val("UP/GU");
    } else if (kode == "GU") {
        $('#Beban').val("UP/GU");
    } else {
        $('#Beban').val(kode);
    }
    //gridspjrincikegiatan();
    $('#pilihall').click(function () {
        $(':checkbox').prop('checked', this.checked);
        cekall(this);
        hitungnilaispj();
    });

});

// global variable
var sisaspjResult;
var totalawal;
var kodebuatbeban;
var totalindex = 0;
var kodeaktif;


function setkb(kode) {
    kodebuatbeban = kode;
}

function cekall(obj) {
    if ($(obj).is(":checked")) {
        $("#kegiatantable :input[type='text']").attr("readonly", false);
    } else {
        $("#kegiatantable :input[type='text']").attr("readonly", "readonly");
    }
}
function getbanyakspdrinci() {
    $.getJSON(getbasepath() + "/spj/json/getlistspjrincibanyak", {idskpd: $("#idskpd").val(), idKegiatan: $("#idKegiatan").val(), tahunAnggaran: $("#tahun").val(), idspj: $("#idspj").val(), beban: $('#kodeBebanPop').val()},
    function (result) {
        $('#banyakrinci').val(result);
        console.log("banyakrinci di getbanyakspdrinci() ===  " + $('#banyakrinci').val());
        /*
         var beban = $('#kodeBeban').val();
         console.log("beban ===  " + beban);
         
         var dgKegiatan = document.getElementById("kegiatantable");
         //var rowlength = dgKegiatan.rows.length;
         //console.log("banyakrinci ===  " + $('#banyakrinci').val());
         
         if (beban == "UP/GU" || beban == "UP") {
         for (var i = 0; i < result; i++) {
         console.log("i == " + i);
         dgKegiatan.rows[0].cells[5].style.display = "none";
         }
         }
         */
    });
}

function cekbeban(beban) {
    //console.log("beban ===  "+beban);

    if (beban == "UP/GU" || beban == "UP") {
        $('#kegiatantable').hide();
        $('#upgukegiatantable').show();
        gridspjrincikegiatanupgu();
    } else { // TU
        $('#upgukegiatantable').hide();
        $('#kegiatantable').show();
        gridspjrincikegiatan();
    }

    gettotalpagu();

}

function gridspjrincikegiatan() {

    //bootbox.alert("Kode beban == "+ $('#kodeBebanPop').val());
    var urljson = getbasepath() + "/spj/json/getlistspjrincikegiatan";
    if (typeof myTable2 == 'undefined') {
        myTable2 = $('#kegiatantable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bAutoWidth": false,
            "bFilter": false,
            "aLengthMenu": [[50, 100, 200, -1], [50, 100,200, "All"]],
            "iDisplayLength": 200,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function () {
                // formatnumberonkeyup() ;
                $(".inputmoneyspj").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "idkegiatan", value: $('#idKegiatan').val()},
                {name: "idspj", value: $('#idspj').val()},
                {name: "beban", value: $('#kodeBebanPop').val()});
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) {
                /* var spjTotal = 0;
                 var sisaspjtotal = 0;
                 
                 if (aaData.length > 0) {
                 for (var i = iStart; i < iEnd; i++) {
                 spjTotal += parseFloat(aaData[i]['spjrinci']['nilai_spj']);
                 sisaspjtotal += parseFloat(aaData[i]['spjrinci']['sisa_spj']);
                 }
                 }
                 $("#totalspj").val(accounting.formatNumber(spjTotal, 2, '.', ","));
                 $("#totalsisaspj").val(accounting.formatNumber(spjTotal, 2, '.', ","));*/
                //totalawal = spjTotal;
            },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var isceked = aData['spjrinci']['nilai_spj'] > 0 ? 'checked' : '';
                var readonly = 'readonly';
                if (isceked === 'checked') {
                    readonly = '';
                }
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                totalindex = totalindex + 1;

                var nilaiUPGUTU = accounting.formatNumber(aData['spjrinci']['nilai_UPGUTU'], 2, '.', ",");
                var nilaiUPGUTUtext = "<input type='text' name='nilaiUPGUTU" + aData['idBl'] + "' id='nilaiUPGUTU" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilaiUPGUTU + "' />"
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBl'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + aData['idBl'] + ") " + isceked + " />";
                var sisaspj = accounting.formatNumber(aData['spjrinci']['sisa_spj'], 2, '.', ",");
                var sisaspjtext = "<input type='text' name='sisaspj" + aData['idBl'] + "' id='sisaspj" + aData['idBl'] + "' size='50'  class='inputmoney'  readonly='true'   value='" + sisaspj + "' />"
                var sudahspj = accounting.formatNumber(aData['spjrinci']['sudah_spj'], 2, '.', ",");
                var sudahspjtext = "<input type='text' name='sudahspj" + aData['idBl'] + "' id='sudahspj" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + sudahspj + "'  />"
                var nilaispj = aData['spjrinci']['nilai_spj'];// accounting.formatNumber(aData['spjrinci']['nilai_spj'], 2, '.', ",");
                var nilaispjtext = "<input type='text' name='nilaispj" + aData['idBl'] + "' id='nilaispj" + aData['idBl'] + "'  style='text-align: right'   class='inputmoneyspj'  value='" + nilaispj + "' " + readonly + " onkeyup='validanilaisisisaspj(" + aData['idBl'] + ")'   />"; //onkeyup='pasangvalidatebesarperfield(" + aData['idBl'] + ");hitungnilaispj()'
                var idspjrinci = "<input type='hidden' name='idbas" + aData['idBl'] + "' id='idbas" + aData['idBl'] + "' value='" + aData['akun']['idAkun'] + "'   />"
                var idbas = "<input type='hidden' name='idbas" + aData['idBl'] + "' id='idbas" + aData['idBl'] + "' value='" + aData['akun']['idAkun'] + "'   />"
                var idbl = "<input type='hidden' name='idbl" + aData['idBl'] + "' id='idbl" + aData['idBl'] + "' value='" + aData['idBl'] + "'   />"
                var nilails = accounting.formatNumber(aData['spjrinci']['nilai_LS'], 2, '.', ",");
                var nilailstext = "<input type='text' name='nilails" + aData['idBl'] + "' id='nilails" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilails + "' />"
                var nilaispd = accounting.formatNumber(aData['spjrinci']['nilai_SPD'], 2, '.', ",");
                var nilaispdtext = "<input type='text' name='nilaispd" + aData['idBl'] + "' id='nilaispd" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilaispd + "' />"

                var namaakun = "<textarea id='namaakun" + index + "'readonly style='border:none;margin:0;width:280px;'>" + aData['akun']['namaAkun'] + "</textarea>";
                $('td:eq(2)', nRow).html(namaakun);
                $('td:eq(3)', nRow).html(nilaispd);
                $('td:eq(4)', nRow).html(nilails);
                $('td:eq(5)', nRow).html(nilaiUPGUTU);
                $('td:eq(6)', nRow).html(sudahspj);
                $('td:eq(7)', nRow).html(sisaspjtext);
                $('td:eq(8)', nRow).html(nilaispjtext);
                $('td:eq(0)', nRow).html(index);
                $('td:eq(9)', nRow).html(inputcek + idbas + idbl);

                $('#rincirows').val(index);// untuk cek beban

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun.kodeAkun", "bSortable": false, sClass: "center", "sWidth": "5%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": false, "sWidth": "8%"},
                {"mDataProp": "akun.namaAkun", "bSortable": false, "sWidth": "23%"},
                {"mDataProp": "spjrinci.nilai_SPD", "bSortable": false, "sWidth": "9%", sClass: "kanan"},
                {"mDataProp": "spjrinci.nilai_LS", "bSortable": false, "sWidth": "9%", sClass: "kanan"},
                {"mDataProp": "spjrinci.nilai_UPGUTU", "bSortable": false, "sWidth": "9%", sClass: "kanan"},
                {"mDataProp": "spjrinci.sudah_spj", "bSortable": false, "sWidth": "9%", sClass: "kanan"},
                {"mDataProp": "spjrinci.sisa_spj", "bSortable": false, "sWidth": "10%", sClass: "kanan"},
                {"mDataProp": "spjrinci.nilai_spj", "bSortable": false, "sWidth": "15%", sClass: "kanan"},
                {"mDataProp": "akun.namaAkun", "bSortable": false, "sWidth": "3%", sClass: "center"}

            ]
        });
    }
    else
    {
        myTable2.fnClearTable(0);
        myTable2.fnDraw();
    }
    getbanyakspdrinci();
}

function gridspjrincikegiatanupgu() {
   // console.log("kode aktif = " + kodeaktif);
    var urljson = getbasepath() + "/spj/json/getlistspjrincikegiatan";
    if (typeof upguTable == 'undefined') {
        upguTable = $('#upgukegiatantable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bAutoWidth": false,
            "bFilter": false,
            "aLengthMenu": [[50, 100, 200, -1], [50, 100,200, "All"]],
            "iDisplayLength": 200,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function () {
                // formatnumberonkeyup() ;
                $(".inputmoneyspj").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "idkegiatan", value: $('#idKegiatan').val()},
                {name: "idspj", value: $('#idspj').val()},
                //{name: "kodeaktif", value: kodeaktif},
                {name: "beban", value: $('#kodeBebanPop').val()});
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) {

            },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var isceked = aData['spjrinci']['nilai_spj'] > 0 ? 'checked' : '';
                var readonly = 'readonly';
                if (isceked === 'checked') {
                    readonly = '';
                }
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                totalindex = totalindex + 1;

                var nilaiUPGUTU = accounting.formatNumber(aData['spjrinci']['nilai_UPGUTU'], 2, '.', ",");
                var nilaiUPGUTUtext = "<input type='text' name='nilaiUPGUTU" + aData['idBl'] + "' id='nilaiUPGUTU" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilaiUPGUTU + "' />"
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBl'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + aData['idBl'] + ") " + isceked + " />";
                var sisaspj = accounting.formatNumber(aData['spjrinci']['sisa_spj'], 2, '.', ",");
                var sisaspjtext = "<input type='text' name='sisaspj" + aData['idBl'] + "' id='sisaspj" + aData['idBl'] + "' size='50'  class='inputmoney'  readonly='true'   value='" + sisaspj + "' />"
                var sudahspj = accounting.formatNumber(aData['spjrinci']['sudah_spj'], 2, '.', ",");
                var sudahspjtext = "<input type='text' name='sudahspj" + aData['idBl'] + "' id='sudahspj" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + sudahspj + "'  />"
                var nilaispj = aData['spjrinci']['nilai_spj'];// accounting.formatNumber(aData['spjrinci']['nilai_spj'], 2, '.', ",");
                var nilaispjtext = "<input type='text' name='nilaispj" + aData['idBl'] + "' id='nilaispj" + aData['idBl'] + "'  style='text-align: right'   class='inputmoneyspj'  value='" + nilaispj + "' " + readonly + " onkeyup='validanilaisisisaspj(" + aData['idBl'] + ")'   />"; //onkeyup='pasangvalidatebesarperfield(" + aData['idBl'] + ");hitungnilaispj()'
                var idspjrinci = "<input type='hidden' name='idbas" + aData['idBl'] + "' id='idbas" + aData['idBl'] + "' value='" + aData['akun']['idAkun'] + "'   />"
                var idbas = "<input type='hidden' name='idbas" + aData['idBl'] + "' id='idbas" + aData['idBl'] + "' value='" + aData['akun']['idAkun'] + "'   />"
                var idbl = "<input type='hidden' name='idbl" + aData['idBl'] + "' id='idbl" + aData['idBl'] + "' value='" + aData['idBl'] + "'   />"
                var nilails = accounting.formatNumber(aData['spjrinci']['nilai_LS'], 2, '.', ",");
                var nilailstext = "<input type='text' name='nilails" + aData['idBl'] + "' id='nilails" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilails + "' />"
                var nilaispd = accounting.formatNumber(aData['spjrinci']['nilai_SPD'], 2, '.', ",");
                var nilaispdtext = "<input type='text' name='nilaispd" + aData['idBl'] + "' id='nilaispd" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilaispd + "' />"

                var namaakun = "<textarea id='namaakun" + index + "'readonly style='border:none;margin:0;width:280px;'>" + aData['akun']['namaAkun'] + "</textarea>";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(namaakun);
                $('td:eq(3)', nRow).html(nilaispd);
                $('td:eq(4)', nRow).html(nilails);
                //$('td:eq(5)', nRow).html(nilaiUPGUTU);
                $('td:eq(5)', nRow).html(sudahspj);
                $('td:eq(6)', nRow).html(sisaspjtext);
                $('td:eq(7)', nRow).html(nilaispjtext);
                $('td:eq(8)', nRow).html(inputcek + idbas + idbl);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun.kodeAkun", "bSortable": false, sClass: "center", "sWidth": "5%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": false, "sWidth": "8%"},
                {"mDataProp": "akun.namaAkun", "bSortable": false, "sWidth": "23%"},
                {"mDataProp": "spjrinci.nilai_SPD", "bSortable": false, "sWidth": "9%", sClass: "kanan"},
                {"mDataProp": "spjrinci.nilai_LS", "bSortable": false, "sWidth": "9%", sClass: "kanan"},
                {"mDataProp": "spjrinci.sudah_spj", "bSortable": false, "sWidth": "9%", sClass: "kanan"},
                {"mDataProp": "spjrinci.sisa_spj", "bSortable": false, "sWidth": "10%", sClass: "kanan"},
                {"mDataProp": "spjrinci.nilai_spj", "bSortable": false, "sWidth": "15%", sClass: "kanan"},
                {"mDataProp": "akun.namaAkun", "bSortable": false, "sWidth": "3%", sClass: "center"}

            ]
        });
    }
    else
    {
        upguTable.fnClearTable(0);
        upguTable.fnDraw();
    }
    getbanyakspdrinci();
}

function enablerow(obj, idbl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbl);
}
function setdisabled(param, idbl) {
    $("#nilaispj" + idbl).attr("readonly", param);

    if (param == false) {
        var nilaispj = accounting.unformat($("#nilaispj" + idbl).val(), ",");
        var sisaspj = accounting.unformat($("#sisaspj" + idbl).val(), ",");
        var nilaiisian = accounting.formatNumber((nilaispj == 0 ? sisaspj : nilaispj), 2, '.', ",")
        $("#nilaispj" + idbl).val(nilaiisian);
    }
    hitungnilaispj();
}

function pasangvalidatebesarperfield(idbl) {
    var nilaispj = accounting.unformat($("#nilaispj" + idbl).val(), ",");
    var sisaspj = accounting.unformat($("#sisaspj" + idbl).val(), ",");
    var status = nilaispj > sisaspj ? false : true;

    if (!status) {
        bootbox.alert("Nilai SPJ tidak boleh lebih besar dari Sisa SPJ", function () {
            $("#nilaispj" + idbl).val(accounting.formatNumber(sisaspj, 2, '.', ","));
            $("#nilaispj" + idbl).focus();
            hitungnilaispj();
        });
    } else {
        return true;
    }
}

function hitungnilaispj() {
    var kode = $('#kodeBeban').val();
    if (kode == "UP/GU" || kode == "UP" || kode == "GU") {
        var searchIDs = $("#upgukegiatantable input:checkbox:checked").not("#pilihall").map(function () {
            return $(this).val();
        }).get();
        var total = 0;
        $.each(searchIDs, function (idx, data) {
            total += parseFloat(accounting.unformat($("#nilaispj" + data).val(), ","));
        })
        $("#sumspjup").val(accounting.formatNumber(total, 2, '.', ","));
        $("#totalspjhidden").val(total);
    } else if (kode == "TU") {
        var searchIDs = $("#kegiatantable input:checkbox:checked").not("#pilihall").map(function () {
            return $(this).val();
        }).get();
        var total = 0;
        $.each(searchIDs, function (idx, data) {
            total += parseFloat(accounting.unformat($("#nilaispj" + data).val(), ","));
        })
        $("#sumspjtu").val(accounting.formatNumber(total, 2, '.', ","));
        $("#totalspjhidden").val(total);
    }
    //var searchIDs = $("#upgukegiatantable input:checkbox:checked").not("#pilihall").map(function () {

}

function submitnilaispj( ) {
    var total = $("#totalspjhidden").val();//accounting.unformat($("#totalspj").val(), ",");
    var sisa2 = accounting.formatNumber(sisaspjResult, 2, '.', ",");
    var kodebebann = $("#kodeBeban").val();
    var sisa = accounting.formatNumber($("#totalsisaspj").val(), 2, '.', ",");
    console.log("Sisa SPJ di SUBMIT NILAI == " + sisaspjResult);
    console.log("Ket beban == " + kodebebann);
    var sisa_uang = accounting.unformat($("#sisaUang").val(), ",");
    
    
    //if (total > sisaspjResult && kodebebann !== "TU") {
    if (total > sisa_uang && kodebebann !== "TU") {
        //bootbox.alert("Total SPJ Tidak Boleh Lebih Besar dari Sisa SPJ -- Sisa Uang Saat Ini adalah Rp. " + sisa2);
        bootbox.alert("Total SPJ Tidak Boleh Lebih Besar dari Sisa Uang Saat Ini");

    } else {
        var varurl = getbasepath() + "/spj/json/prosessimpanrincikegiatan";
        var dataac = [];
        $(".cekpilih:checked").each(function () {
            var datapeg = {
                idbl: $("#idbl" + $(this).val()).val(),
                idbas: $("#idbas" + $(this).val()).val(),
                idspj: $("#idspj").val(),
                idkegiatan: $("#idKegiatan").val(),
                nilaispj: $("#nilaispj" + $(this).val()).val(),
                kodebeban: $("#Beban").val()
            }
            dataac.push(datapeg);
        });
        return   $.ajax({
            type: "POST",
            url: varurl,
            contentType: "text/plain; charset=utf-8",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(dataac)
        }).always(function (data) {
            gridspjrincikegiatan();
            bootbox.alert(data.responseText);
            var myvalue = $("#idKegiatan").val();
            $('#tanda', window.parent.document).val(myvalue).trigger('change');
            parent.$.fancybox.close();
        });
    }

}
function ubahnilaispj( ) {
    var totasisa = totalawal + sisaspjResult;
    var total = accounting.unformat($("#totalspj").val(), ",");
    var sisa = accounting.formatNumber(totasisa, 2, '.', ",");

    if (total > totasisa) {
        bootbox.alert("Total SPJ Tidak Boleh Lebih Besar dari Sisa SPJ -- Sisa SPJ Saat Ini adalah Rp. " + sisa);
    } else {
        var varurl = getbasepath() + "/spj/json/prosesubahrincikegiatan/" + $("#idspj").val() + "/" + $("#idKegiatan").val() + "/" + $("#kodeBeban").val() + "";
        var dataac = [];
        $(".cekpilih:checked").each(function () {
            var datapeg = {
                idbl: $("#idbl" + $(this).val()).val(),
                idbas: $("#idbas" + $(this).val()).val(),
                idspj: $("#idspj").val(),
                idkegiatan: $("#idKegiatan").val(),
                nilaispj: $("#nilaispj" + $(this).val()).val(),
                kodebeban: $("#kodeBeban").val()
            }
            dataac.push(datapeg);
        });
        return   $.ajax({
            type: "POST",
            url: varurl,
            contentType: "text/plain; charset=utf-8",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(dataac)
        }).always(function (data) {
            gridspjrincikegiatan();
            bootbox.alert(data.responseText);
            var myvalue = $("#kodeBeban").val();
            $('#tanda', window.parent.document).val(myvalue).trigger('change');
            parent.$.fancybox.close();
        });
    }
}

function hapusspjrinci() {
    var varurl = getbasepath() + "/spj/json/prosesdeleteupdaterincikegiatan/" + $("#idspj").val() + "";
    var dataac = [];
    var datapeg = {"idspj": $("#idspj").val()};
    dataac.push(datapeg);
    return   $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function (data) {
        gridspjrincikegiatan();
        bootbox.alert(data.responseText);
        //var myvalue = $("#idKegiatan").val();
        parent.$.fancybox.close();

    });
}

function validasisisaspj() {

    var idskpd = $("#idskpd").val();

    $.getJSON(getbasepath() + "/spj/json/validasisisaspj", {idskpd: idskpd},
    function (result) {
        sisaspjResult = result.aData[0]['sisaSpj'];

    });
}

function gridspjrincikegiatanedit() {

    //bootbox.alert("Kode beban #kodeBeban == "+ $('#kodeBeban').val());
    var urljson = getbasepath() + "/spj/json/getlistspjrincikegiatan";
    if (typeof myTable2 == 'undefined') {
        myTable2 = $('#kegiatantable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bAutoWidth": false,
            "bFilter": false,
            "aLengthMenu": [[50, 100, 200, -1], [50, 100,200, "All"]],
            "iDisplayLength": 200,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function () {
                // formatnumberonkeyup() ;
                $(".inputmoneyspj").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "idkegiatan", value: $('#idKegiatan').val()},
                {name: "idspj", value: $('#idspj').val()},
                {name: "beban", value: $('#kodeBeban').val()});
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) {
                var spjTotal = 0;
                var sisaspjtotal = 0;
                if (aaData.length > 0) {
                    for (var i = iStart; i < iEnd; i++) {
                        spjTotal += parseFloat(aaData[i]['spjrinci']['nilai_spj']);
                        sisaspjtotal += parseFloat(aaData[i]['spjrinci']['sisa_spj']);
                    }
                }
                $("#totalspj").val(accounting.formatNumber(spjTotal, 2, '.', ","));
                $("#totalsisaspj").val(accounting.formatNumber(spjTotal, 2, '.', ","));
                totalawal = spjTotal;
            },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var isceked = aData['spjrinci']['nilai_spj'] > 0 ? 'checked' : '';
                var readonly = 'readonly';
                if (isceked === 'checked') {
                    readonly = '';
                }
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                totalindex = totalindex + 1;

                var nilaiUPGUTU = accounting.formatNumber(aData['spjrinci']['nilai_UPGUTU'], 2, '.', ",");
                var nilaiUPGUTUtext = "<input type='text' name='nilaiUPGUTU" + aData['idBl'] + "' id='nilaiUPGUTU" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilaiUPGUTU + "' />"
                //var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBl'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + aData['idBl'] + ") " + isceked + " />";
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + aData['idBl'] + ") " + isceked + " />";

                var sisaspj = accounting.formatNumber(aData['spjrinci']['sisa_spj'], 2, '.', ",");
                var sisaspjtext = "<input type='text' name='sisaspj" + aData['idBl'] + "' id='sisaspj" + aData['idBl'] + "' size='50'  class='inputmoney'  readonly='true'   value='" + sisaspj + "' />"
                var sudahspj = accounting.formatNumber(aData['spjrinci']['sudah_spj'], 2, '.', ",");
                var sudahspjtext = "<input type='text' name='sudahspj" + aData['idBl'] + "' id='sudahspj" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + sudahspj + "'  />"
                var nilaispj = aData['spjrinci']['nilai_spj'];// accounting.formatNumber(aData['spjrinci']['nilai_spj'], 2, '.', ",");
                var nilaispjtext = "<input type='text' name='nilaispj" + aData['idBl'] + "' id='nilaispj" + aData['idBl'] + "'  style='text-align: right'   class='inputmoneyspj'  value='" + nilaispj + "' " + readonly + " onkeyup='validanilaisisisaspj(" + aData['idBl'] + ")'   />"; //onkeyup='pasangvalidatebesarperfield(" + aData['idBl'] + ");hitungnilaispj()'
                var idspjrinci = "<input type='hidden' name='idbas" + aData['idBl'] + "' id='idbas" + aData['idBl'] + "' value='" + aData['akun']['idAkun'] + "'   />"
                var idbas = "<input type='hidden' name='idbas" + aData['idBl'] + "' id='idbas" + aData['idBl'] + "' value='" + aData['akun']['idAkun'] + "'   />"
                var idbl = "<input type='hidden' name='idbl" + aData['idBl'] + "' id='idbl" + aData['idBl'] + "' value='" + aData['idBl'] + "'   />"
                var nilails = accounting.formatNumber(aData['spjrinci']['nilai_LS'], 2, '.', ",");
                var nilailstext = "<input type='text' name='nilails" + aData['idBl'] + "' id='nilails" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilails + "' />"
                var nilaispd = accounting.formatNumber(aData['spjrinci']['nilai_SPD'], 2, '.', ",");
                var nilaispdtext = "<input type='text' name='nilaispd" + aData['idBl'] + "' id='nilaispd" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilaispd + "' />"

                $('td:eq(3)', nRow).html(nilaispd);
                $('td:eq(4)', nRow).html(nilails);
                $('td:eq(5)', nRow).html(nilaiUPGUTU);
                $('td:eq(6)', nRow).html(sudahspj);
                $('td:eq(7)', nRow).html(sisaspjtext);
                $('td:eq(8)', nRow).html(nilaispjtext);
                $('td:eq(0)', nRow).html(index);
                $('td:eq(9)', nRow).html(inputcek + idbas + idbl);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun.kodeAkun", "bSortable": false, sClass: "center", "sWidth": "5%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": false, "sWidth": "8%"},
                {"mDataProp": "akun.namaAkun", "bSortable": false, "sWidth": "23%"},
                {"mDataProp": "spjrinci.nilai_SPD", "bSortable": false, "sWidth": "9%", sClass: "kanan"},
                {"mDataProp": "spjrinci.nilai_LS", "bSortable": false, "sWidth": "9%", sClass: "kanan"},
                {"mDataProp": "spjrinci.nilai_UPGUTU", "bSortable": false, "sWidth": "9%", sClass: "kanan"},
                {"mDataProp": "spjrinci.sudah_spj", "bSortable": false, "sWidth": "9%", sClass: "kanan"},
                {"mDataProp": "spjrinci.sisa_spj", "bSortable": false, "sWidth": "10%", sClass: "kanan"},
                {"mDataProp": "spjrinci.nilai_spj", "bSortable": false, "sWidth": "15%", sClass: "kanan"},
                {"mDataProp": "akun.namaAkun", "bSortable": false, "sWidth": "3%", sClass: "center"}

            ]
        });
    }
    else
    {
        myTable2.fnClearTable(0);
        myTable2.fnDraw();
    }
    getbanyakspdrinci();
}

function cek() {
    bootbox.alert("Total Index  == " + totalindex);

}

function validanilaisisisaspj(index) {

    var nilaispj = parseFloat(accounting.unformat($('#nilaispj' + index).val(), ","));
    var sisaspj = parseFloat(accounting.unformat($('#sisaspj' + index).val(), ","));

    if (nilaispj > sisaspj) {
        bootbox.alert("Nilai SPJ tidak boleh melebihi sisa SPJ.");
        //$('#nilaispj' + index).val(accounting.formatNumber(sisaspj, 2, '.', ","));
        $('#nilaispj' + index).autoNumeric('set', sisaspj);
    }
    //bootbox.alert("total index = "+totalindex);

    hitungnilaispj();
    
}

function getkodeaktif() {
    var idskpd = $("#idskpd").val();
    var tahun = $("#tahun").val();
    $.getJSON(getbasepath() + "/spj/json/getkodeaktif", {idskpd: idskpd, tahun: tahun},
    function (result) {
        var kode = result.aData['kodeAktif'];
        kodeaktif = kode;

        //console.log("getkodeaktif() - kode = " + kode);
    });
}

function gettotalpagu() {
    var idskpd = $("#idskpd").val();
    var tahun = $("#tahun").val();
    $.getJSON(getbasepath() + "/spj/json/gettotalpagu", {idskpd: idskpd, tahun: tahun, idkegiatan: $('#idKegiatan').val(), idspj: $('#idspj').val()},
    function (result) {
        var pagu = result.aData['nilaiPaguUp'];
        var sisa_uang = result.aData['sisaUang'];
        var totspp = result.aData['totalSpp'];
        var totspj = result.aData['totalSpj'];
        var persen = result.aData['persentase'];
        var entryspj =result.aData['entrySpj']; 


        $("#nilaiPaguUp").val(accounting.formatNumber(pagu, 2, '.', ","));
        $("#sisaUang").val(accounting.formatNumber(sisa_uang, 2, '.', ","));
        $("#totalSpp").val(accounting.formatNumber(totspp, 2, '.', ","));
        $("#totalSpj").val(accounting.formatNumber(totspj, 2, '.', ","));
        $("#persentase").val(accounting.formatNumber(persen, 2, '.', ","));
        $("#entrySpj").val(accounting.formatNumber(entryspj, 2, '.', ","));

    });

}
