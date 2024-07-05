$(document).ready(function() {
    $("#bulanAkhir").rules('add', {bulanLebihBesar: "#bulanAwal"});
    $("#spdBTLMasterform").validate();
    loadupdate(getbasepath() + "/spd/pengajuanbiayarev/json/getlistspddetailbyskpddantahun");
    statusbreadcrum();

});

function getpagudanspd(id) {
    var idskpd = $("#idskpd").val();
    var idSpd = $("#idSpd").val();
    var tahunAnggaran = $("#tahunAnggaran").val();
    var noSpd = $("#noSpd").val();
    if (noSpd && $.trim(noSpd).length > 0) {
        $("#btlspdtablebody").empty();
        gridspdbtllist(getbasepath() + "/spd/pengajuanbiayarev/json/getlistspddetailbyskpddantahun", id, idSpd, tahunAnggaran);
    }
    $.getJSON(getbasepath() + "/spd/pengajuanbiayarev/json/getanggarandanspd/" + id,
            function(result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });
    statusbreadcrum();
}
function simpan() {
    var idskpd = $("#idskpd").val();
    var idSpd = $("#idSpd").val();
    var tahunAnggaran = $("#tahunAnggaran").val();
    submispddetail(getbasepath() + '/spd/pengajuanbiayarev/json/addspddetail', getbasepath() + "/spd/pengajuanbiayarev/json/getlistspddetailbyskpddantahun", idskpd, idSpd, tahunAnggaran);
}
function pindahhalamanadepan() {
    if ($("#idskpd").val().trim()) {
        window.location.replace(getbasepath() + "/spd/pengajuanbiayarev/biaya/indexskpd/" + $("#idskpd").val().trim());
    } else {
        window.location.replace(getbasepath() + "/spd/pengajuanbiayarev/biaya/indexbiayarev");
    }
}
function gridspdbtllist(urljson, idskpd, idspd, tahun) {
    if (typeof myTable == 'undefined') {
        // console.log(" idskpd >>   : " + idskpd);
        myTable = $('#btlspdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "iDisplayLength": 25,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[0, "asc"]],
            "fnDrawCallback": function() {
               // $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $("#idskpd").val()},
                {name: "idspd", value: idspd},
                {name: "tahun", value: tahun},
                {name: "addoredit", value: $("#isadd").val()}
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
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var idspdakundetail = "<input type='hidden' id='idakundetail" + index + "' value='" + aData['idAkun'] + "'  name='idakundetail" + index + "'  />"
                var idspddetail = "<input type='hidden' id='idspddetailX" + aData['idAkun'] + "' value='" + aData['idSpdRinci'] + "'  name='idspddetailX" + aData['idAkun'] + "'  />"
                var nilaiAnggaranTAPD = "<input type='hidden'   id='idAnggaranTAPDX" + aData['idAkun'] + "'  name='idAnggaranTAPDX" + aData['idAkun'] + "'    value='" + aData['nilaiAnggaranTAPD'] + "'       />";
                var nilaiSisaTAPD = "<input type='hidden'   id='idSisaTAPDX" + aData['idAkun'] + "'  name='idSisaTAPDX" + aData['idAkun'] + "'    value='" + aData['nilaiAnggaranSPDSisa'] + "'       />";

                $('td:eq(0)', nRow).html(index + idspddetail + idspdakundetail);
                $('td:eq(3)', nRow).html(nilaiSisaTAPD + nilaiAnggaranTAPD + accounting.formatNumber(aData['nilaiAnggaranTAPD'], 2, '.', ","));
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['nilaiAnggaranSPDSisa'], 2, '.', ","));
                var inputidbtl = "<input type='hidden'   id='idbtlX" + aData['idAkun'] + "'  name='idbtlX" + aData['idAkun'] + "'    value='" + aData['idBtl'] + "'       />";
                var cekSpd = $("#isadd").val();//aData['nilaiAnggaranSPDEdit'];

                var nilaiAnggaranSPDCurrent = (cekSpd == "1" ? aData['nilaiAnggaranSPDBaru'] : aData['nilaiAnggaranSPDEdit']);
                // console.log(cekSpd + " nilaiAnggaranSPDEdit  " + (cekSpd == "1") + " nilaiAnggaranSPDCurrent " + nilaiAnggaranSPDCurrent + "  " + aData['nilaiAnggaranSPDBaru']);
                var inputspd = "<input type='text'   id='nilaiSpdX" + aData['idAkun'] + "'    name='nilaiSpdX" + aData['idAkun'] + "'  onblur='formatnilai(this)' onfocus='unformatnilai(this)'    value='" + accounting.formatNumber(nilaiAnggaranSPDCurrent, 2, '.', ",") + "'   class='inputmoney'   onkeyup=pasangvalidatebesarperfield(" + aData['idAkun'] + ")     />";
                $('td:eq(5)', nRow).html(inputspd + inputidbtl);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idAkun", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeAkun", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "namaAkun", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "nilaiAnggaranTAPD", "bSortable": false, sClass: "right"},
                {"mDataProp": "idAkun", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiAnggaranSPDCurrent", "bSortable": false, sClass: "right"}
            ]
        });



    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function statusbreadcrum() {
    if ($("#isadd").val() == '1') {
        $("#statusaddedit").text(" (tambah) " + $("#skpd").val());
    } else {
        $("#statusaddedit").text(" (ubah)  " + $("#skpd").val());
    }
}

function loadupdate(varurl) {
    var idskpd = $("#idskpd").val();
    var idSpd = $("#idSpd").val();
    var tahunAnggaran = $("#tahunAnggaran").val();
    var noSpd = $("#noSpd").val();
    if (idskpd) {
        $("#nospdform").val(idSpd);
        getpagudanspd(idskpd);
        if (noSpd && $.trim(noSpd).length > 0) {
            gridspdbtllist(varurl, idskpd, idSpd, tahunAnggaran);
        }
    }
}

function pasangvalidatebesarperfield(idakun) {
    var nilaiSpd =$("#nilaiSpdX" + idakun).val();
    var nilaiangg = $("#idSisaTAPDX" + idakun).val();
   // var status = nilaiSpd >= nilaiangg ? false : true;
    var status = nilaiSpd >= nilaiangg && nilaiSpd > 0 ? false : true;
    if (!status) {
        $("#nilaiSpdX" + idakun).prop('readonly', true);
        bootbox.alert("Nilai SPD tidak boleh lebih besar dari nilai anggaran", function() {
            $("#nilaiSpdX" + idakun).val(nilaiangg);
            $("#nilaiSpdX" + idakun).focus();
            $("#nilaiSpdX" + idakun).prop('readonly', false);
            //return false;
        });

    } else {
        $("#nilaiSpdX" + idakun).val($("#nilaiSpdX" + idakun).val());
        return true;
    }

}
function pasangvalidatebesar( ) {
    var i = 0;
    $("#formbtlspdtable input[name*='idakundetail']").each(
            function( ) {
                var idakun = $(this).val();
                var nilaiSpd = accounting.unformat($("#nilaiSpdX" + idakun).val(), ",");
                var nilaiangg = $("#idSisaTAPDX" + idakun).val();
                // console.log($("#nilaiSpdX" + idakun).val() + " < nilai spd  :" + nilaiSpd + " > " + nilaiangg + "  " + (nilaiSpd > nilaiangg));
                if (nilaiSpd > nilaiangg) {
                    i++;
                }
            }
    );
    return i == 0 ? true : false;

}
function submispddetail(varurl, urljson, idskpd, idspd, tahun) {
  //  if (pasangvalidatebesar( )) {
        var dataac = $('#formbtlspdtable').serializeObject();
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
            $("#isadd").val(0);
            gridspdbtllist(urljson, idskpd, idspd, tahun);
            bootbox.alert(data.responseText);
        });
  //  }
 /*   else {
        bootbox.alert("Nilai SPD tidak boleh lebih besar dari sisa SPD", function() {
            return false;
        });
        return false;
    }*/
}