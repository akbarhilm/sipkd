$(document).ready(function () {
    $("#bulanAkhir").rules('add', {bulanLebihBesar: "#bulanAwal"});
    $("#spdBTLMasterform").validate();
    loadupdate(getbasepath() + "/spd/pengajuanblrev/json/getlistspddetailbyskpddantahun");
    statusbreadcrum();
    $('#isexist').change(function () {
        var idskpd = $("#idskpd").val();
        var idSpd = $("#idSpd").val();
        var tahunAnggaran = $("#tahunAnggaran").val();
        gridspdbtllist(getbasepath() + "/spd/pengajuanblrev/json/getlistspddetailbyskpddantahun", idskpd, idSpd, tahunAnggaran);
        if ($(this).is(":checked")) {

        } else {

        }
        $('#textbox1').val($(this).is(':checked'));
    });
    $("#kodekegiatanfilter").keyup(function () {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            var idskpd = $("#idskpd").val();
            var idSpd = $("#idSpd").val();
            var tahunAnggaran = $("#tahunAnggaran").val();
            gridspdbtllist(getbasepath() + "/spd/pengajuanblrev/json/getlistspddetailbyskpddantahun", idskpd, idSpd, tahunAnggaran);
        }
    });

    $("#namakegiatanfilter").keyup(function () {
        var panjang = $(this).val().length;
        if (panjang > 3 || panjang == 0) {
            var idskpd = $("#idskpd").val();
            var idSpd = $("#idSpd").val();
            var tahunAnggaran = $("#tahunAnggaran").val();
            gridspdbtllist(getbasepath() + "/spd/pengajuanblrev/json/getlistspddetailbyskpddantahun", idskpd, idSpd, tahunAnggaran);
        }
    });
    $("#linkpopupdetail").fancybox({
        width: '100%',
        height: '100%',
        autoScale: false,
        autoDimensions: false,
        closeClick: false,
        helpers: {
            overlay: {closeClick: false}
        }
    });


});

function getpagudanspd(id) {
    var idskpd = $("#idskpd").val();
    var idSpd = $("#idSpd").val();
    var tahunAnggaran = $("#tahunAnggaran").val();
    var noSpd = $("#noSpd").val();
    statusbreadcrum();
    $.getJSON(getbasepath() + "/spd/pengajuanblrev/json/getanggarandanspdbantuanlangsung/" + id,
            function (result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });
    if (noSpd && $.trim(noSpd).length > 0) {
        $("#btlspdtablebody").empty();
        gridspdbtllist(getbasepath() + "/spd/pengajuanblrev/json/getlistspddetailbyskpddantahun", id, idSpd, tahunAnggaran);
    }


}
function simpan() {
    var idskpd = $("#idskpd").val();
    var idSpd = $("#idSpd").val();
    var tahunAnggaran = $("#tahunAnggaran").val();
    submispddetail(getbasepath() + '/spd/pengajuanblrev/json/addspddetail', getbasepath() + "/spd/pengajuanblrev/json/getlistspddetailbyskpddantahun", idskpd, idSpd, tahunAnggaran);
}
function pindahhalamanadepan() {
    if ($("#idskpd").val().trim()) {
        window.location.replace(getbasepath() + "/spd/pengajuanblrev/bl/indexskpd/" + $("#idskpd").val().trim());
    } else {
        window.location.replace(getbasepath() + "/spd/pengajuanblrev/bl/indexblrev");
    }

}

function gridspdbtllist(urljson, idskpd, idspd, tahun) {
    if (typeof myTable == 'undefined') {
        myTable = $('#btlspdtable').dataTable({
            "bPaginate": true,
            "bAutoWidth": false,
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
            "aaSorting": [[1, "desc"]],
            "fnDrawCallback": function () {
                // $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                parseInt($("#isadd").val()) == 1 ? $("#isexist").prop('disabled', true) : $("#isexist").prop('disabled', false);
            },
            "fnServerParams": function (aoData) {
                var isexist = $("#isexist").is(":checked") && parseInt($("#isadd").val()) != 1 ? '1' : '';
                aoData.push(
                        {name: "idskpd", value: $("#idskpd").val()},
                {name: "idspd", value: idspd},
                {name: "tahun", value: tahun},
                {name: "addoredit", value: $("#isadd").val()},
                {name: "kodekegiatanfilter", value: $("#kodekegiatanfilter").val()},
                {name: "namakegiatanfilter", value: $("#namakegiatanfilter").val()},
                {name: "isexist", value: isexist}
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
                var inputidbtl = "<input type='hidden'   id='idbtlX" + aData['idAkun'] + "'  name='idbtlX" + aData['idAkun'] + "'    value='" + aData['idBtl'] + "'       />";

                var idspdakundetail = "<input type='hidden' id='idakundetailX" + aData['idBtl'] + "' value='" + aData['idAkun'] + "'  name='idakundetailX" + aData['idBtl'] + "'  />";
                var idspddetail = "<input type='hidden' id='idspddetailX" + aData['idBtl'] + "' value='" + aData['idSpdRinci'] + "'  name='idspddetailX" + aData['idBtl'] + "'  />";
                var cekSpd = $("#isadd").val();
                //----------------------------------------------------
                $('td:eq(0)', nRow).html(index + idspddetail + idspdakundetail);
                var hidkodekegiatan = "<input type='hidden' name='kodekegiatan" + aData['idBtl'] + "'  id='kodekegiatan" + aData['idBtl'] + "'  value='" + aData['kodeKegiatan'] + "'  />";
                $('td:eq(1)', nRow).html(aData['kodeKegiatan'] + hidkodekegiatan);

                var namakegiatan = "<textarea id='namakegiatan" + aData['idBtl'] + "'   readonly   style='border:none;margin:0;width:99%;'   >" + aData['namaKegiatan'] + "</textarea>";
                $('td:eq(2)', nRow).html(namakegiatan);
                var nilaiAnggaranTAPD = accounting.formatNumber(aData['nilaiAnggaranTAPD'], 2, '.', ",");
                $('td:eq(3)', nRow).html(nilaiAnggaranTAPD);
                var nilaiAnggaranSPDSisa = accounting.formatNumber(aData['nilaiAnggaranSPDSisa'], 2, '.', ",");
                
              /*  var sisa = aData['nilaiAnggaranSPDSisa'];
                if (sisa < 0)
                {
                    var sisa1 = sisa - sisa;
                    $('td:eq(4)', nRow).html(sisa1);
                }else
                {
                    sisa;
                    $('td:eq(4)', nRow).html(sisa);
                }
                */
                
                var tampil = eval(aData['nilaiAnggaranSPDSisa']) - eval(aData['nilaiAnggaranSPDSudahAda']);
                //  var tampildata = tampil < eval(aData['nilaiAnggaranSPDEdit']) ? tampil : 0;
                var nilaiAnggaranSPDSudahAda = accounting.formatNumber(aData['nilaiAnggaranSPDEdit'], 2, '.', ",");

                $('td:eq(4)', nRow).html(nilaiAnggaranSPDSisa);
                
                $('td:eq(5)', nRow).html(nilaiAnggaranSPDSudahAda);
                $('td:eq(6)', nRow).html("<span class='icon-zoom-in linkpilihan' title='Klik untuk melihat detail' onclick='popupgridspdbtllistbyidkegiatan(" + aData['idBtl'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBtl", "bSortable": false, sClass: "center", "sWidth": "5%"},
                {"mDataProp": "kodeKegiatan", "bSortable": false, sDefaultContent: "-", "sWidth": "8%"},
                {"mDataProp": "namaKegiatan", "bSortable": false, sDefaultContent: "-", "sWidth": "50%"},
                {"mDataProp": "nilaiAnggaranTAPD", "bSortable": false, sClass: "right", "sWidth": "15%"},
                {"mDataProp": "nilaiAnggaranSPDSisa", "bSortable": false, sClass: "right", "sWidth": "15%"},
                {"mDataProp": "nilaiAnggaranSPDSisa", "bSortable": false, sClass: "right", "sWidth": "15%"},
                {"mDataProp": "idBtl", "bSortable": false, sClass: "center", "sWidth": "5%"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function popupgridspdbtllistbyidkegiatan(idgiat) {
    $("#idkegiatan").val(idgiat);
    $("#nospdform").val($("#idSpd").val());
    gridspdbtllistbyidkegiatan(idgiat);
    $("#kodekegiatan").html($("#kodekegiatan" + idgiat).val());
    $("#namakegiatan").html($("#namakegiatan" + idgiat).val());
    $("#linkpopupdetail").click();
}

function gridspdbtllistbyidkegiatan(    ) {
    if (typeof detailTable == 'undefined') {
        detailTable = $('#btlspdbykegiatantable').dataTable({
            "bPaginate": true,
            "bAutoWidth": false,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "iDisplayLength": 25,
            "bScrollAutoCss": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + '/spd/pengajuanblrev/json/getlistspddetailbyidkegiatandantahun',
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function () {
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idkegiatan", value: $("#idkegiatan").val()},
                {name: "idspd", value: $("#idSpd").val()},
                {name: "tahun", value: $("#tahunAnggaran").val()},
                {name: "addoredit", value: $("#isadd").val()}
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

                var idspdakundetail = "<input type='hidden' id='idakundetailX" + index + "' value='" + aData['idAkun'] + "'  name='idakundetailX" + index + "'  />";
                var idspddetail = "<input type='hidden' id='idspddetailX" + aData['idAkun'] + "' value='" + aData['idSpdRinci'] + "'  name='idspddetailX" + aData['idAkun'] + "'  />";
                var idbtl = "<input type='hidden' id='idbtlX" + aData['idAkun'] + "' value='" + aData['idBtl'] + "'  name='idbtlX" + aData['idAkun'] + "'  />";

                var cekSpd = $("#isadd").val();
                //----------------------------------------------------
                $('td:eq(0)', nRow).html(index + idspddetail + idspdakundetail + idbtl);
                $('td:eq(1)', nRow).html(aData['kodeAkun']);
                var namaakun = "<textarea   readonly   id='namaakundetailX" + aData['idAkun'] + "'  style='border:none;margin:0;width:99%;'  >" + aData['namaAkun'] + "</textarea>";
                $('td:eq(2)', nRow).html(namaakun);
                var nilaiAnggaranTAPD = accounting.formatNumber(aData['nilaiAnggaranTAPD'], 2, '.', ",");
                $('td:eq(3)', nRow).html(nilaiAnggaranTAPD);
                var nilaiAnggaranSPDSisa = accounting.formatNumber(aData['nilaiAnggaranSPDSisa'], 2, '.', ",");
                $('td:eq(4)', nRow).html(nilaiAnggaranSPDSisa);
                
               /*  var sisa = aData['nilaiAnggaranSPDSisa'];
                if (sisa < 0)
                {
                    var sisa1 = sisa - sisa;
                    $('td:eq(4)', nRow).html(sisa1);
                }else
                {
                    sisa;
                    $('td:eq(4)', nRow).html(sisa);
                }*/
                
                
                var sisaspd = "<input type='hidden'   id='idSisaTAPDX" + aData['idAkun'] + "'    name='idSisaTAPDX" + aData['idAkun'] + "'   value='" + aData['nilaiAnggaranSPDSisa'] + "'       />";

                var nilaiAnggaranSPDCurrent = (cekSpd == "1" ? aData['nilaiAnggaranSPDBaru'] : aData['nilaiAnggaranSPDEdit']);
                var inputspd = "<input type='text'   id='nilaiSpdX" + aData['idAkun'] + "'    name='nilaiSpdX" + aData['idAkun'] + "'   value='" + accounting.formatNumber(nilaiAnggaranSPDCurrent, 2, '.', ",") + "'   class='inputmoney'   onkeyup=pasangvalidatebesarperfield(" + aData['idAkun'] + ")     />";
                $('td:eq(5)', nRow).html(inputspd + sisaspd);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idAkun", "bSortable": false, sClass: "center", "sWidth": "5%"},
                {"mDataProp": "kodeAkun", "bSortable": false, sDefaultContent: "-", "sWidth": "8%"},
                {"mDataProp": "namaAkun", "bSortable": false, sDefaultContent: "-", "sWidth": "40%"},
                {"mDataProp": "nilaiAnggaranTAPD", "bSortable": false, sClass: "right", "sWidth": "15%"},
                {"mDataProp": "idAkun", "bSortable": false, sClass: "right", "sWidth": "15%"},
                {"mDataProp": "nilaiAnggaranSPDCurrent", "bSortable": false, sClass: "center", "sWidth": "15%"}
            ]
        });
    }
    else
    {
        detailTable.fnClearTable(0);
        detailTable.fnDraw();
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
    var nilaiSpd = eval(accounting.unformat($("#nilaiSpdX" + idakun).val(), ",")) * 10;
    var nilaiangg = $("#idSisaTAPDX" + idakun).val();
    var status = nilaiSpd >= nilaiangg && nilaiSpd > 0 ? false : true;
    if (!status) {
        $("#nilaiSpdX" + idakun).prop('readonly', true);
        bootbox.alert("Nilai SPD tidak boleh lebih besar  dari sisa SPD", function () {
            $("#nilaiSpdX" + idakun).val(accounting.formatNumber(nilaiangg, 2, '.', ","));
            $("#nilaiSpdX" + idakun).focus();
            $("#nilaiSpdX" + idakun).prop('readonly', false);
            return false;
        });

    } else {
        $("#nilaiSpdX" + idakun).val($("#nilaiSpdX" + idakun).val());
        return true;
    }
    /*
     //alert("-->"+nilaiSpd );
     //alert("==>"+nilaiangg );
     
     if (nilaiangg < 0) {
     $("#nilaiSpdX" + idakun).autoNumeric('set', 0);
     if (nilaiSpd > 0) {
     
     bootbox.alert("Nilai SPD tidak boleh lebih besar dari sisa SPD", function () {
     $("#nilaiSpdX" + idakun).autoNumeric('set', nilaiangg);
     
     $("#nilaiSpdX" + idakun).focus();
     $("#nilaiSpdX" + idakun).prop('readonly', false);
     //return false;
     });
     
     }
     } else {
     if (nilaiSpd > nilaiangg) {
     
     bootbox.alert("Nilai SPD tidak boleh lebih besar dari sisa SPD", function () {
     $("#nilaiSpdX" + idakun).autoNumeric('set', nilaiangg);
     
     $("#nilaiSpdX" + idakun).focus();
     $("#nilaiSpdX" + idakun).prop('readonly', false);
     //return false;
     });
     }
     }
     
     $("#nilaiSpdX" + idakun).val($("#nilaiSpdX" + idakun).val());*/
    //return true;


}
function pasangvalidatebesar( ) {
    var i = 0;
    $("#formbtlspdtable input[name*='idakundetail']").each(
            function ( ) {
                var idakun = $(this).val();
                var nilaiSpd = accounting.unformat($("#nilaiSpdX" + idakun).val(), ",");
                var nilaiangg = $("#idSisaTAPDX" + idakun).val();
                // console.log($("#nilaiSpdX" + idakun).val() + " < nilai spd  :" + nilaiSpd + " > " + nilaiangg + "  " + (nilaiSpd > nilaiangg));

                /* if (nilaiSpd > nilaiangg) {
                 i++;
                 }*/

//alert("aaa "+nilaiSpd );
//alert("zzz "+nilaiangg );
                if (nilaiangg < 0) {
                    $("#nilaiSpdX" + idakun).autoNumeric('set', 0);
                    $("#idSisaTAPDX" + idakun).autoNumeric('set', 0);
                    if (nilaiSpd > 0) {

                        bootbox.alert("Nilai SPD tidak boleh lebih besar dari sisa SPD1", function () {
                            $("#nilaiSpdX" + idakun).autoNumeric('set', nilaiangg);

                            $("#nilaiSpdX" + idakun).focus();
                            $("#nilaiSpdX" + idakun).prop('readonly', false);
                            //return false;
                        });

                    }
                } else {
                    if (nilaiSpd > nilaiangg) {

                        bootbox.alert("Nilai SPD tidak boleh lebih besar dari sisa SPD2", function () {
                            $("#nilaiSpdX" + idakun).autoNumeric('set', nilaiangg);

                            $("#nilaiSpdX" + idakun).focus();
                            $("#nilaiSpdX" + idakun).prop('readonly', false);
                            //  return false;
                        });
                    }
                }

                $("#nilaiSpdX" + idakun).val($("#nilaiSpdX" + idakun).val());
                return true;


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
    //}
    /*   else {
     bootbox.alert("Nilai SPD tidak boleh lebih besar dari sisa anggaran", function () {
     return false;
     });
     return true;
     
     }*/
}
function tutuppopup() {
    parent.$.fancybox.close();
}