$(document).ready(function() {
     var kode = $('#kodeBeban').val();
     if (kode == "UP"){
   $('#Beban').val("UP/GU");    
     }else if(kode == "GU"){
   $('#Beban').val("UP/GU");    
     }else{
   $('#Beban').val(kode);    
     }
    gridspjrincikegiatan();
    $('#pilihall').click(function() {
        $(':checkbox').prop('checked', this.checked);
        cekall(this);
        hitungnilaispj();
    });

});
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
});
function cekall(obj) {
    if ($(obj).is(":checked")) {
        $("#kegiatantable :input[type='text']").attr("readonly", false);
    } else {
        $("#kegiatantable :input[type='text']").attr("readonly", "readonly");
    }
}
function getbanyakspdrinci() {
    $.getJSON(getbasepath() + "/spj/json/getlistspjrincibanyak", {idskpd: $("#idskpd").val(), idKegiatan: $("#idKegiatan").val(), tahunAnggaran: $("#tahun").val(), idspj: $("#idspj").val()},
    function(result) {
        $('#banyakrinci').val(result);
    });
}

function gridspjrincikegiatan() {
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
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "idkegiatan", value: $('#idKegiatan').val()},
                {name: "idspj", value: $('#idspj').val()},
                {name: "beban", value: $('#kodeBeban').val()} );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
                var spjTotal = 0;
                var sisaspjtotal = 0;
                if (aaData.length > 0) {
                    for (var i = iStart; i < iEnd; i++) {
                        spjTotal += parseFloat(aaData[i]['spjrinci']['nilai_spj']);
                        sisaspjtotal += parseFloat(aaData[i]['spjrinci']['sisa_spj']);
                    }
                }
                $("#totalspj").val(accounting.formatNumber(spjTotal, 2, '.', ","))
                $("#totalsisaspj").val(accounting.formatNumber(spjTotal, 2, '.', ","))

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
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var isceked = aData['spjrinci']['nilai_spj'] > 0 ? 'checked' : '';
                var readonly = 'readonly';
                if (isceked === 'checked') {
                    readonly = '';
                }
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var nilaiUPGUTU = accounting.formatNumber(aData['spjrinci']['nilai_UPGUTU'], 2, '.', ",");
                var nilaiUPGUTUtext = "<input type='text' name='nilaiUPGUTU" + aData['idBl'] + "' id='nilaiUPGUTU" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilaiUPGUTU + "' />"
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBl'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + aData['idBl'] + ") " + isceked + " />";
                var sisaspj = accounting.formatNumber(aData['spjrinci']['sisa_spj'], 2, '.', ",");
                var sisaspjtext = "<input type='text' name='sisaspj" + aData['idBl'] + "' id='sisaspj" + aData['idBl'] + "' size='50'  class='inputmoney'  readonly='true'   value='" + sisaspj + "' />"
                var sudahspj = accounting.formatNumber(aData['spjrinci']['sudah_spj'], 2, '.', ",");
                var sudahspjtext = "<input type='text' name='sudahspj" + aData['idBl'] + "' id='sudahspj" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + sudahspj + "' />"
                var nilaispj = accounting.formatNumber(aData['spjrinci']['nilai_spj'], 2, '.', ",");
                var nilaispjtext = "<input type='text' name='nilaispj" + aData['idBl'] + "' id='nilaispj" + aData['idBl'] + "'  class='inputmoney'  value='" + nilaispj + "' " + readonly + " onkeyup='pasangvalidatebesarperfield(" + aData['idBl'] + ");hitungnilaispj()'  />"
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
                {"mDataProp": "akun.kodeAkun", "bSortable": true, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, "sWidth": "11%"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, "sWidth": "17%"},
                {"mDataProp": "spjrinci.nilai_SPD", "bSortable": true, "sWidth": "12%", sClass: "kanan", },
                {"mDataProp": "spjrinci.nilai_LS", "bSortable": true, "sWidth": "12%", sClass: "kanan", },
                {"mDataProp": "spjrinci.nilai_UPGUTU", "bSortable": true, "sWidth": "10%", sClass: "kanan", },
                {"mDataProp": "spjrinci.sudah_spj", "bSortable": true, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "spjrinci.sisa_spj", "bSortable": true, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "spjrinci.nilai_spj", "bSortable": false, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "akun.namaAkun", "bSortable": false, sClass: "center", "sWidth": "4%"}
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
function enablerow(obj, idbl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbl)
}
function setdisabled(param, idbl) {
    $("#nilaispj" + idbl).attr("readonly", param);
  //  $("#sisaspj" + idbl).attr("readonly", param);
    if (param == false) {
        var nilaispj = accounting.unformat($("#nilaispj" + idbl).val(), ",");
        var sisaspj = accounting.unformat($("#sisaspj" + idbl).val(), ",");
        var nilaiisian = accounting.formatNumber((nilaispj == 0 ? sisaspj : nilaispj), 2, '.', ",")
        $("#nilaispj" + idbl).val(nilaiisian)
    }
    hitungnilaispj();
}

function pasangvalidatebesarperfield(idbl) {
    var nilaispj = accounting.unformat($("#nilaispj" + idbl).val(), ",");
    var sisaspj = accounting.unformat($("#sisaspj" + idbl).val(), ",");
    var status = nilaispj > sisaspj ? false : true;
    // console.log(status+"  "+nilaispp+"  "+nilaispd);
    if (!status) {
        bootbox.alert("Nilai SPJ tidak boleh lebih besar dari Sisa SPJ", function() {
            $("#nilaispj" + idbl).val(accounting.formatNumber(sisaspj, 2, '.', ","));
            $("#nilaispj" + idbl).focus();
            hitungnilaispj();
        });
    } else {
        return true;
    }
}
function hitungnilaispj() {
    var searchIDs = $("#kegiatantable input:checkbox:checked").not("#pilihall").map(function() {
        return $(this).val();
    }).get();
    var total = 0;
    $.each(searchIDs, function(idx, data) {
        total += parseFloat(accounting.unformat($("#nilaispj" + data).val(), ","));
    })
   // $("#totalsisaspj").val(accounting.formatNumber(total, 2, '.', ","));
    $("#totalspj").val(accounting.formatNumber(total, 2, '.', ","));
}

function submitnilaispj( ) {
    var varurl = getbasepath() + "/spj/json/prosessimpanrincikegiatan";
    var dataac = [];
    $(".cekpilih:checked").each(function() {
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
    }).always(function(data) {
        gridspjrincikegiatan();
        bootbox.alert(data.responseText);
        var myvalue = $("#idKegiatan").val();
        // $('#tanda', window.parent.document).val($("#Beban").val()).trigger('change');
        $('#tanda', window.parent.document).val(myvalue).trigger('change');
        parent.$.fancybox.close();

//        window.parent.document.gridspjrinci();

    });
}
function ubahnilaispj( ) {
    //hapusspjrinci();
    var varurl = getbasepath() + "/spj/json/prosesubahrincikegiatan/"+$("#idspj").val()+"/"+ $("#idKegiatan").val() +"/"+ $("#kodeBeban").val() +"";
    var dataac = [];
    $(".cekpilih:checked").each(function() {
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
    }).always(function(data) {
        gridspjrincikegiatan();
        bootbox.alert(data.responseText);
        var myvalue = $("#kodeBeban").val();
        // $('#tanda', window.parent.document).val($("#Beban").val()).trigger('change');
        $('#tanda', window.parent.document).val(myvalue).trigger('change');
        parent.$.fancybox.close();
    });    
}

function hapusspjrinci() {
    var varurl = getbasepath() + "/spj/json/prosesdeleteupdaterincikegiatan/"+ $("#idspj").val() +"";
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
    }).always(function(data) {
        gridspjrincikegiatan();
        bootbox.alert(data.responseText);
        var myvalue = $("#idKegiatan").val();
        // $('#tanda', window.parent.document).val($("#Beban").val()).trigger('change');
       // $('#tanda', window.parent.document).val(myvalue).trigger('change');
        parent.$.fancybox.close();

//        window.parent.document.gridspjrinci();

    });
    

}


function setkodebeban(value) {
    // alert("sini");
    //  alert(value);
    // $("#beban").val($("#kodeBeban").val());
    // $("#beban").val(value);
}

