$(document).ready(function () {
    formatnumberonkeyup();
    $("#bulanAkhir").rules('add', {bulanLebihBesar: "#bulanAwal"});
    $("#spdBTLMasterform").validate();
    loadupdate(getbasepath() + "/spd/pengajuanbtlbantuanrev/json/getlistspddetailbyskpddantahun");
    statusbreadcrum();
    $("#kodekegiatanfilter").keyup(function () {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            var idskpd = $("#idskpd").val();
            var idSpd = $("#idSpd").val();
            var tahunAnggaran = $("#tahunAnggaran").val();
            gridspdbtllist(getbasepath() + "/spd/pengajuanbtlbantuanrev/json/getlistspddetailbyskpddantahun", idskpd, idSpd, tahunAnggaran);
        }
    });

    $("#namakegiatanfilter").keyup(function () {
        var panjang = $(this).val().length;
        if (panjang > 3 || panjang == 0) {
            var idskpd = $("#idskpd").val();
            var idSpd = $("#idSpd").val();
            var tahunAnggaran = $("#tahunAnggaran").val();
            gridspdbtllist(getbasepath() + "/spd/pengajuanbtlbantuanrev/json/getlistspddetailbyskpddantahun", idskpd, idSpd, tahunAnggaran);
        }
    });

});

function getpagudanspd(id) {
    var idskpd = $("#idskpd").val();
    var idSpd = $("#idSpd").val();
    var tahunAnggaran = $("#tahunAnggaran").val();
    var noSpd = $("#noSpd").val();
    statusbreadcrum();
    $.getJSON(getbasepath() + "/spd/pengajuanbtlbantuanrev/json/getanggarandanspd/" + id,
            function (result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });
    if (noSpd && $.trim(noSpd).length > 0) {
        $("#btlspdtablebody").empty();
        gridspdbtllist(getbasepath() + "/spd/pengajuanbtlbantuanrev/json/getlistspddetailbyskpddantahun", id, idSpd, tahunAnggaran);
    }


}
function simpan() {
    var idskpd = $("#idskpd").val();
    var idSpd = $("#idSpd").val();
    var tahunAnggaran = $("#tahunAnggaran").val();
    submispddetail(getbasepath() + '/spd/pengajuanbtlbantuanrev/json/addspddetail', getbasepath() + "/spd/pengajuanbtlbantuan/json/getlistspddetailbyskpddantahun", idskpd, idSpd, tahunAnggaran);
}
function pindahhalamanadepan() {
    window.location.replace(getbasepath() + "/spd/pengajuanbtlbantuanrev/btlbantuan/indexbtlbantuanrev");

}
function gridspdbtllist(urljson, idskpd, idspd, tahun) {
    if (typeof myTable == 'undefined') {
        //   console.log(">>>>>>>>   : " +  $('#btlspdtable'));
        myTable = $('#btlspdtable').dataTable({
            "bPaginate": true,
            "bAutoWidth": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "iDisplayLength": 25,
            "bScrollAutoCss": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function () {
                // $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
                /* $('.nilaispdentry').on("input", function () {
                 var dInput = this.value;
                 var arrid = this.id.split("X");
                 //console.log(accounting.unformat(dInput,",") + " arrid " + arrid)
                 var nilai = eval(accounting.unformat(dInput, ",")) * 10;
                 pasangvalidatebesarperfield(arrid[1], nilai);
                 });*/

            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $("#idskpd").val()},
                {name: "idspd", value: idspd},
                {name: "tahun", value: tahun},
                {name: "addoredit", value: $("#isadd").val()},
                {name: "kodekegiatanfilter", value: $("#kodekegiatanfilter").val()},
                {name: "namakegiatanfilter", value: $("#namakegiatanfilter").val()}
                );
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
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var inputidbtl = "<input type='hidden'   id='idbtlX" + index + "'  name='idbtlX" + index + "'    value='" + aData['idBtl'] + "'       />";

                var idspdakundetail = "<input type='hidden' id='idakundetailX" + aData['idBtl'] + "' value='" + aData['idAkun'] + "'  name='idakundetailX" + aData['idBtl'] + "'  />"
                var idspddetail = "<input type='hidden' id='idspddetailX" + aData['idBtl'] + "' value='" + aData['idSpdRinci'] + "'  name='idspddetailX" + aData['idBtl'] + "'  />"
                var nilaiAnggaranTAPD = "<input type='hidden'   id='idAnggaranTAPDX" + aData['idBtl'] + "'    name='idAnggaranTAPDX" + aData['idBtl'] + "'    value='" + aData['nilaiAnggaranTAPD'] + "'       />";
                var nilaiSisaTAPD = "<input type='hidden'   id='idSisaTAPDX" + aData['idBtl'] + "' class='inputmoney' name='idSisaTAPDX" + aData['idBtl'] + "'    value='" + aData['nilaiAnggaranSPDSisa'] + "'       />";
                var cekSpd = $("#isadd").val();//aData['nilaiAnggaranSPDEdit'];
                var nilaiAnggaranSPDCurrent = (cekSpd == "1" ? aData['nilaiAnggaranSPDBaru'] : aData['nilaiAnggaranSPDEdit']);
                var inputspd = "<input type='text'   id='nilaiSpdX" + aData['idBtl'] + "'    name='nilaiSpdX" + aData['idBtl'] + "'   value='" + nilaiAnggaranSPDCurrent + "'   class='inputmoney nilaispdentry'    onkeyup=pasangvalidatebesarperfield(" + aData['idBtl'] + ")     />";
                //----------------------------------------------------
                $('td:eq(0)', nRow).html(index + idspddetail + idspdakundetail);
                $('td:eq(1)', nRow).html(aData['kodeKegiatan']);
                $('td:eq(2)', nRow).html("<input type='text'  value='" + aData['namaKegiatan'] + "' readonly   style='border:none;margin:0;width:99%;'  />");
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['nilaiAnggaranSPDMurni'], 2, '.', ","));
               
                $('td:eq(5)', nRow).html(nilaiSisaTAPD + nilaiAnggaranTAPD + accounting.formatNumber(aData['nilaiAnggaranTAPD'], 2, '.', ","));
                $('td:eq(6)', nRow).html(accounting.formatNumber(aData['nilaiAnggaranSPDSisa'], 2, '.', ","));
                $('td:eq(7)', nRow).html(inputspd + inputidbtl);
                $('td:eq(8)', nRow).html(accounting.formatNumber(aData['nilaiSPDYangSudahDiserap'], 2, '.', ","));
               
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBtl", "bSortable": false, sClass: "center", "sWidth": "5%"},
                {"mDataProp": "kodeKegiatan,", "bSortable": false, sDefaultContent: "-", "sWidth": "8%"},
                {"mDataProp": "namaKegiatan,", "bSortable": false, sDefaultContent: "-", "sWidth": "20%"},
                {"mDataProp": "kodeAkun", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "7%"},
                {"mDataProp": "nilaiAnggaranSPDMurni", "bSortable": false, sClass: "right", "sWidth": "15%"},
                {"mDataProp": "nilaiAnggaranTAPD", "bSortable": false, sClass: "right", "sWidth": "15%"},
                {"mDataProp": "idAkun", "bSortable": false, sClass: "right", "sWidth": "15%"},
                {"mDataProp": "nilaiAnggaranSPDCurrent", "bSortable": false, sClass: "right", "sWidth": "15%"},
                {"mDataProp": "nilaiSPDYangSudahDiserap", "bSortable": false, sClass: "right", "sWidth": "15%"}
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
    var nilaiSpd = parseFloat($("#nilaiSpdX" + idakun).autoNumeric('get'));//;//eval(accounting.unformat($("#nilaiSpdX" + idakun).val(), ",")) * 10;
    var nilaiangg =  $("#idSisaTAPDX" + idakun).autoNumeric('get') ;
    var status = nilaiSpd >= nilaiangg && nilaiSpd > 0 ? false : true;
    // var status = nilaiSpd > nilaiangg ? false : true;
    // console.log(" nilaiSpd " + nilaiSpd + "  nilaiangg " + nilaiangg)
    if (!status) {
        $("#nilaiSpdX" + idakun).prop('readonly', true);

        bootbox.alert("Nilai SPD tidak boleh lebih besar dari sisa SPD", function() {
           if(nilaiangg < 0){
                 $("#nilaiSpdX" + idakun).autoNumeric('set', 0);
           }else{
                $("#nilaiSpdX" + idakun).autoNumeric('set', nilaiangg);
           }

            $("#nilaiSpdX" + idakun).focus();
            $("#nilaiSpdX" + idakun).prop('readonly', false);
            //return false;
        });

    } else {
        $("#nilaiSpdX" + idakun).val($("#nilaiSpdX" + idakun).val());
        return true;
    }
    
/*
    var nilaiSpd = parseFloat($("#nilaiSpdX" + idakun).autoNumeric('get'));// ;//eval(accounting.unformat($("#nilaiSpdX" + idakun).val(), ",")) * 10;
    var nilaiangg = parseFloat($("#idAnggaranTAPDX" + idakun).val());
   // var status = nilaiSpd >= nilaiangg && nilaiSpd > 0 ? false : true;
    var status = nilaiSpd <= nilaiangg && nilaiSpd > 0 ? false : true;
    //var status = nilaiSpd <= nilaiangg;
    // console.log(" nilaiSpd " + nilaiSpd + " nilaiangg==> " + nilaiangg + " idakun " + idakun + " status " + status)
    if (!status) {
        $("#nilaiSpdX" + idakun).prop('readonly', true);
        bootbox.alert("Nilai SPD tidak boleh lebih besar dari sisa SPD", function () {
            $("#nilaiSpdX" + idakun).focus();
            $("#nilaiSpdX" + idakun).prop('readonly', false);
            $("#nilaiSpdX" + idakun).autoNumeric('set',nilaiangg);
            $("#nilaiSpdX" + idakun).empty();
            //  return false;
        });

    }/* else {
     $("#nilaiSpdX" + idakun).val($("#nilaiSpdX" + idakun).val());
     return true;
     }*/

}
function pasangvalidatebesar( ) {
    var i = 0;
    formatnumberonkeyup();
    $("#formbtlspdtable input[name*='idakundetail']").each(
            function ( ) {
                var idakun = $(this).val();
                var nilaiSpd = $("#nilaiSpdX" + idakun).val();//accounting.unformat($("#nilaiSpdX" + idakun).val(), ",");
                var nilaiangg = parseFloat($("#idAnggaranTAPDX" + idakun).val());//$("#idSisaTAPDX" + idakun).val();
                // console.log($("#nilaiSpdX" + idakun).val() + " < nilai spd  :" + nilaiSpd + " > " + nilaiangg + "  " + (nilaiSpd > nilaiangg));
                if (nilaiSpd > nilaiangg) {
                    i++;
                }
            }
    );
     
    return i == 0 ? true : false;

}
function submispddetail(varurl, urljson, idskpd, idspd, tahun) {
    $("#idskpdkoordinator").val($("#idskpd").val());
   // if (pasangvalidatebesar( )) {
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
        }).always(function (data) {
            $("#isadd").val(0);
            gridspdbtllist(urljson, idskpd, idspd, tahun);
            bootbox.alert(data.responseText);
        });
 //   }
  /*  else {
        bootbox.alert("Nilai SPD tidak boleh lebih besar dari nilai anggaran", function () {
            return false;
        });
        return false;
    }*/
}