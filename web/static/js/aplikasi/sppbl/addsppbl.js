$(document).ready(function () {
    document.getElementById("potonganUangMuka").disabled = true;
    
    var d = new Date();
    var m = d.getMonth()+1;
    var y = d.getFullYear();
    
    if (m < 10) {
        m = '0' + m;
    }
    
    var bulan = m+"/"+y;
    
    $('#bulan').val(bulan);
    
    hapus();
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
    //gridspprinci();
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
        idspp: $("#id").val(), nomorBast: $('#nomorBast').val()},
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
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 25,
            "sDom": '<"toolbar">flrtip',
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function () {
                //$("#spprincitable :input").not("input[type='checkbox']").attr("readonly", "readonly");
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                // gettotalspddanspp( );
                $(".inputmoney2").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idkegiatan", value: $('#idKegiatan').val()},
                {name: "idakun", value: $('#idAkun').val()},
                {name: "idspp", value: $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()},
                {name: "spdno", value: $('#nospdfilter').val()},
                {name: "nomorBast", value: $('#nomorBast').val()},
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

                    $("#totalspd").val(accounting.formatNumber(pageTotal, 2, '.', ","));
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
                var idceklog = aData['akun']['idAkun'];
                var idspd = "<input type='hidden' id='idspd" + index + "'   name='idspd" + index + "'  value='" + aData['idSpd'] + "'  />";
                var idkegiatan = "<input type='hidden' id='idkegiatan" + index + "'   name='idkegiatan" + index + "'  value='" + aData['kegiatan']['idKegiatan'] + "'  />";
                var noSpd = "<input type='hidden' name='noSpd" + index + "'  id='noSpd" + index + "'     kode='noSpd' value='" + aData['noSpd'] + "' />";
                var isceked = aData['nilaiSpp'] > 0 ? 'checked' : '';
                var readonly = 'readonly'
                //if (isceked === 'checked') {
                //  readonly = '';
                //}
                var idakun = "<input type='hidden' name='idakun" + index + "'     id='idakun" + index + "' value='" + aData['akun']['idAkun'] + "' />";
                var idblhidden = "<input type='hidden' name='idblhidden" + index + "' id='idblhidden" + index + "' value='" + aData['idBl'] + "' />";
                var inputcek = "<input type='checkbox' class='cekpilih' disabled='true' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";
                var namaakun = "<textarea  name='namaAkun'  style='border:none;margin:0;width:99%;' readonly  kode='namaAkun'  >" + aData['akun']['namaAkun'] + "</textarea>"
                
                var nilaispd = accounting.formatNumber(aData['nilaiSpd'], 2, '.', ",");
                var nilaispdtext = "<input type='text' name='nilaispd" + index + "' id='nilaispd" + index + "'  class='inputmoney'  readonly='true'   value='" + nilaispd + "' />"
                var nilaispp = accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");//aData['nilaiSpp']; //accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");//aData['nilaiSpp'];//accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var nilaispptext = "<input type='text' name='nilaispp" + index + "' id='nilaispp" + index + "'  class='inputmoney'  readonly='true'   onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'   value='" + nilaispp + "'   />"
                
                $('td:eq(0)', nRow).html(index + idspd + idkegiatan + idblhidden + idakun);
                $('td:eq(3)', nRow).html(namaakun);
                $('td:eq(4)', nRow).html(nilaispdtext);
                $('td:eq(5)', nRow).html(nilaispptext);
                $('td:eq(6)', nRow).html(inputcek);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBl", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noSpd", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, "sWidth": "32%"},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "kanan", "sWidth": "20%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "20%"},
                {"mDataProp": "idBl", "bSortable": false, sClass: "center", "sWidth": "4%"}
            ]
        });
        
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    getbanyakspdrinci( );
    getStatusUangMuka( );
}
function enablerow(obj, idbl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbl);
}
function setdisabled(param, idbl) {
    // $("#nilaiSppSisa" + idbl).attr("readonly", param);
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
    var nilaisppsisa = accounting.unformat($("#nilaiSppSisa" + idbl).val(), ",");
    var totalspp = accounting.unformat($("#totalspp" + idbl).val(), ",");
    var totalspd = accounting.unformat($("#totalspd" + idbl).val(), ",");
    var status = nilaispp <= nilaispd && nilaispp <= nilaisppsisa && totalspp <= totalspd;
    // console.log((nilaispp < nilaispd) + "  " + status + "  " + nilaispp + "  " + nilaispd + " "   );
    if (!status) {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari nilai SPD", function () {
            //  $("#nilaispp" + idbl).val(accounting.formatNumber(nilaisppsisa, 2, '.', ","));
            $("#nilaispp" + idbl).autoNumeric('set', nilaisppsisa);
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
        try {
            $('#totalsisa').val(accounting.formatNumber(result.V_SPD, 2, '.', ","));
            $('#totalspp').val(accounting.formatNumber(result.V_SPP, 2, '.', ","));
        } catch (e) {

        }
    });

}

function hapus() {

    $("#noKontrak").val("");
    $("#nilaiKontrak").val("");
    $("#idKegiatan").val("");
    $("#idAkun").val("");
    $("#namaKegiatan").val("");
    $("#idBast").val("");
    $("#noBast").val("");

}

function getStatusUangMuka( ) {
    $.getJSON(getbasepath() + "/bl/json/getstatusuangmuka",
            {
                tahunAnggaran: $("#tahun").val(),
                idskpd: $("#idskpd").val(),
                nomorBast: $("#nomorBast").val(),
                idkegiatan: $("#idKegiatan").val()
            },
    function (result) {
        if (result == 1 || result == '1')
        {
            $('#potonganUangMuka').attr("checked", true);
            $('#kodeUangMuka').val(1);
        } else {
            $('#kodeUangMuka').val(0);
        }

    });



}
 