$(document).ready(function () {
    gridspprinci();
    $('#popup1').hide();
    $('#kodeUangMuka').click(function () {
        if ($(this).is(':checked')) {
            $('#popup1').show();
            $('#popup2').hide();

        } else {
            $('#popup1').hide();
            $('#popup2').show();
        }

    });
});
$(document).ready(function () {
    gridspprinci();
    if ($("#idskpd").val()) {
        //getpagudanspd($("#idskpd").val())
    }
    $('#pilihall').click(function () {
        $(':checkbox').prop('checked', this.checked);
        cekall(this);
        hitungnilaispp();
    });
    $("#kodekegiatanfilter").keyup(function () {
        var panjang = $(this).val().length;
        if (panjang > 3) {
            gridspprinci();
            gettotalspddanspp( );
        }
    });
    $("#nospdfilter").keyup(function () {
        var panjang = $(this).val().length;
        if (panjang > 1) {
            gridspprinci();
            gettotalspddanspp( );
        }
    });

});
function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/bl/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function (result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });

}
function getbanyakspdrinci( ) {
    $.getJSON(getbasepath() + "/bl/json/getlistspdblbanyak", {
        tahunAnggaran: $("#tahun").val(),
        idskpd: $("#idskpd").val(),
        idkegiatan: $("#idKegiatan").val(),
        idakun: $("#idAkun").val(),
        idspp: $("#id").val()},
    function (result) {
        $('#banyakrinci').val(result);
    });

}
function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/bl/indexsppbl/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/bl/indexsppbl");
    }

}
function cekall(obj) {
    if ($(obj).is(":checked")) {
        $("#spprincitable :input[type='text']").attr("readonly", false);
    } else {
        $("#spprincitable :input[type='text']").attr("readonly", "readonly");
    }
}
function gridspprinci() {
    var urljson = getbasepath() + "/bl/json/getlistspdbl";
    if (typeof myTable == 'undefined') {
        myTable = $('#spprincitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "autoWidth": false,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sDom": '<"toolbar">flrtip',
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function () {
                //$("#spprincitable :input").not("input[type='checkbox']").attr("readonly", "readonly");
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                gettotalspddanspp( );
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idkegiatan", value: $('#idKegiatan').val()},
                {name: "idakun", value: $('#idAkun').val()},
                {name: "idspp", value: $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()},
                {name: "spdno", value: $('#nospdfilter').val()},
                {name: "kodekegiatan", value: $('#kodekegiatanfilter').val()}
                );
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) {
                try {

                    var pageTotal = 0;
                    var spptotal = 0;
                    if (aaData.length > 0) {
                        for (var i = iStart; i < iEnd; i++) {
                            pageTotal += parseFloat(aaData[i]['nilaiSppSisa']);
                            spptotal += parseFloat(aaData[i]['nilaiSpp']);
                        }
                    }

                    $("#totalspd").val(accounting.formatNumber(spptotal, 2, '.', ","));
                    $("#totalspp").val(accounting.formatNumber(spptotal, 2, '.', ","));

                } catch (e) {

                }


            }
            ,
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
                var idspd = "<input type='hidden' id='idspd" + aData['idBl'] + "'   name='idspd" + aData['idBl'] + "'  value='" + aData['idSpd'] + "'  />";
                var idkegiatan = "<input type='hidden' id='idkegiatan" + aData['idBl'] + "'   name='idkegiatan" + aData['idBl'] + "'  value='" + aData['kegiatan']['idKegiatan'] + "'  />";
                var idakun = "<input type='hidden' id='idakun" + aData['idBl'] + "'   name='idakun" + aData['idBl'] + "'  value='" + aData['akun']['idAkun'] + "'  />";
                var noSpd = "<input type='hidden' name='noSpd" + aData['idBl'] + "'  id='noSpd" + aData['idBl'] + "'     kode='noSpd' value='" + aData['noSpd'] + "' />";
                var isceked = aData['nilaiSppSisa'] > 0 ? 'checked' : '';
                var readonly = 'readonly'
                if (isceked === 'checked') {
                    readonly = '';
                }
                var idblhidden = "<input type='hidden' name='idblhidden" + index + "'     id='idblhidden" + index + "' value='" + aData['idBl'] + "' />";

                var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBl'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + aData['idBl'] + ") " + isceked + " />";
                var namaakun = "<input type='text' name='namaAkun'  style='border:none;margin:0;width:99%;' readonly  kode='namaAkun' value='" + aData['akun']['namaAkun'] + "' />"
                var namaKegiatan = "<input type='text' name='namaKegiatan'  style='border:none;margin:0;width:99%;' readonly  kode='namaKegiatan' value='" + aData['kegiatan']['namaKegiatan'] + "' />"
                var nilaispd = accounting.formatNumber(aData['nilaiSpd'], 2, '.', ",");
                var nilaispdtext = "<input type='text' name='nilaispd" + aData['idBl'] + "' id='nilaispd" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilaispd + "' />"
                var nilaispp = accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var nilaispptext = "<input type='text' name='nilaispp" + aData['idBl'] + "' id='nilaispp" + aData['idBl'] + "'  class='inputmoney'  " + readonly + "    onkeyup='pasangvalidatebesarperfield(" + aData['idBl'] + ");hitungnilaispp()'   value='" + nilaispp + "'   />"
                var nilaisppsisa = accounting.formatNumber(aData['nilaiSppSisa'], 2, '.', ",");
                var nilaisppsisatext = "<input type='text' name='nilaiSppSisa" + aData['idBl'] + "' id='nilaiSppSisa" + aData['idBl'] + "'  class='inputmoney'  " + readonly + "    onkeyup='pasangvalidatebesarperfield(" + aData['idBl'] + ");hitungnilaispp()'   value='" + nilaisppsisa + "'   />"
                var nilaisppsebelum = accounting.formatNumber(aData['nilaiSppSebelum'], 2, '.', ",");
                var nilaisppsebelumtext = "<input type='text' name='nilaiSppSebelum" + aData['idBl'] + "' id='nilaiSppSebelum" + aData['idBl'] + "'  class='inputmoney'  " + readonly + "    onkeyup='pasangvalidatebesarperfield(" + aData['idBl'] + ");hitungnilaispp()'   value='" + nilaisppsebelum + "'   />"
                var nilaispphidden = "<input type='hidden' name='nilaispporg" + aData['idBl'] + "'     id='nilaispporg" + aData['idBl'] + "' value='" + aData['nilaiSpp'] + "' />";
                $('td:eq(0)', nRow).html(index + idspd + idkegiatan + idakun + idblhidden);
                $('td:eq(3)', nRow).html(namaakun);
                $('td:eq(4)', nRow).html(nilaispdtext);
                $('td:eq(5)', nRow).html(nilaisppsebelumtext);
                $('td:eq(6)', nRow).html(nilaisppsisatext);
                $('td:eq(7)', nRow).html(nilaispptext);
                $('td:eq(8)', nRow).html(inputcek);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBl", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noSpd", "bSortable": true, "sWidth": "8%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, "sWidth": "17%"},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "kanan", "sWidth": "13%"},
                {"mDataProp": "nilaiSppSebelum", "bSortable": false, sClass: "kanan", "sWidth": "13%"},
                {"mDataProp": "nilaiSppSisa", "bSortable": false, sClass: "kanan", "sWidth": "13%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "13%"},
                {"mDataProp": "idBl", "bSortable": false, sClass: "center", "sWidth": "5%"}
            ]
        });
        //var buttoncetakall = "<input   id='hapusrinci' type='button' class='button'  name='hapusrinci' value='Hapus SPP Rinci'  onclick=popUpCetakReport() />";
        //$("div.toolbar").append('<div style="float: right"> &nbsp;&nbsp;' + buttoncetakall + '</div><br/> ');

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    getbanyakspdrinci( );
}
function enablerow(obj, idbl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbl)
}
function setdisabled(param, idbl) {
    $("#nilaispp" + idbl).attr("readonly", param);
    $("#nilaiSppSisa" + idbl).attr("readonly", param);
    if (param == false) {
        var nilaispp = accounting.unformat($("#nilaispp" + idbl).val(), ",");
        var nilaisisa = accounting.unformat($("#nilaiSppSisa" + idbl).val(), ",");
        var nilaiisian = accounting.formatNumber((nilaispp == 0 ? nilaisisa : nilaispp), 2, '.', ",")
        $("#nilaispp" + idbl).val(nilaiisian)
    }
    hitungnilaispp();
}

function pasangvalidatebesarperfield(idbl) {
    var nilaispp = accounting.unformat($("#nilaispp" + idbl).val(), ",");
    var nilaispd = accounting.unformat($("#nilaispd" + idbl).val(), ",");
    var strtotalAngaran = $("#nilaiKontrak").val();
    var totalAngaran = strtotalAngaran.split('.').join('') // accounting.unformat($("#totalAngaran" + idbl).val(), ",");
    var totalspp = accounting.unformat($("#totalspp" + idbl).val(), ",");
    var status = nilaispp <= nilaispd && nilaispp < totalAngaran && totalspp <= totalAngaran;
    console.log((nilaispp < totalAngaran) + "  " + status + "  " + nilaispp + "  " + nilaispd + " " + totalAngaran);
    if (!status) {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari nilai SPD", function () {
            $("#nilaispp" + idbl).val(accounting.formatNumber(nilaispd, 2, '.', ","));
            $("#nilaispp" + idbl).focus();
            hitungnilaispp();
        });

    } else {
        return true;
    }
}

function hitungnilaispp() {
    var searchIDs = $("#spprincitable input:checkbox:checked").not("#pilihall").map(function () {
        return $(this).val();
    }).get();
    var total = 0;
    $.each(searchIDs, function (idx, data) {
        total += parseFloat(accounting.unformat($("#nilaispp" + data).val(), ","));
    })
    $("#totalspp").val(accounting.formatNumber(total, 2, '.', ","));


}

function gettotalspddanspp( ) {
    $.getJSON(getbasepath() + "/bl/json/gettotalspddanspp",
            {
                tahunAnggaran: $("#tahun").val(),
                idskpd: $("#idskpd").val(),
                idspp: $("#id").val(),
                kodekegiatan: $("#kodekegiatanfilter").val(),
                spdno: $("#nospdfilter").val()
            },
    function (result) {        
        $('#totalspd').val(accounting.formatNumber(result.NILAISPD, 2, '.', ","));
        $('#totalspp').val(accounting.formatNumber(result.NILAISPP, 2, '.', ","));
    });

}
 