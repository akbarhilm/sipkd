$(document).ready(function() {
    getDataBankDki();
    
    var namaYayasan = $("#namaYayasan");
    var  namaPenerima = $("#namaPenerima");
    gridspprinci();
    if ($("#idskpd").val()) {
      getpagudanspd($("#idskpd").val())
    }
     $('#pilihall').click(function () {
        $(':checkbox').prop('checked', this.checked);
        cekall(this);
        hitungnilaispp();
    });
    
    
    $("#nospdfilter").keyup(function () {
        var panjang = $(this).val().length;
        if (panjang >= 1) {
            gridspprinci();
                        gettotalspddanspp( );
        }
    });

 });

// var global
var response76 = "00";


function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/biaya/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function(result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });

}
function getbanyakspdrinci( ) {
    $.getJSON(getbasepath() + "/biaya/json/getlistspdbiayabanyak", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val(),idspp:$("#id").val()},
    function(result) {
        $('#banyakrinci').val(result);
    });

}
function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/biaya/indexbiaya/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/biaya/indexbiaya");
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
    var urljson = getbasepath() + "/biaya/json/getlistspdbl";
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
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                //$("#spprincitable :input").not("input[type='checkbox']").attr("readonly", "readonly");
                //$(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
                
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspp", value: $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()},
                {name: "spdno", value: $('#nospdfilter').val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
                var pageTotal = 0;
                var spptotal = 0;
                
                //  console.log(iEnd+" ---> "+aaData.length);
                if (aaData.length > 0) {
                    for (var i = iStart; i < iEnd; i++) {
                        console.log(Number(aaData[i]['nilaiSpp']));
                        pageTotal += Number(aaData[i]['nilaiSpd']);
                        spptotal += Number(aaData[i]['nilaiSpp']);
                    }
                }

                $("#totalspd").val(accounting.formatNumber(pageTotal, 2, '.', ","));
                $("#totalspp").val(accounting.formatNumber(spptotal, 2, '.', ","));

            }
            ,
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
                var idbiaya = "<input type='hidden' id='idbiaya" + index + "'   name='idbiaya" + index + "'  value='" + aData['idBiaya'] + "'  />";
                
                var idspd = "<input type='hidden' id='idspd" + index + "'   name='idspd" + index + "'  value='" + aData['idSpd'] + "'  />";
                var idakun = "<input type='hidden' id='idakun" + index + "'   name='idakun" + index + "'  value='" + aData['akun']['idAkun'] + "'  />";
                var isceked = aData['nilaiSpp'] > 0 ? 'checked' : '';
                var readonly = 'readonly'
                if (isceked === 'checked') {
                    readonly = '';
                }
                
                var stat = aData['statusSpd'];
                if ( stat != 'P' )
                {
                    var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";
                }else
                {
                    var inputcek = '-';
                }
                
                var namaakun = "<textarea name='namaAkun'  style='border:none;margin:0;width:99%;'  kode='namaAkun'  >" + aData['akun']['namaAkun'] + "</textarea>"
                var nilaispdtext = "<input type='text' name='nilaispd" + index + "' id='nilaispd" + index + "'  class='inputmoney'  readonly='true'   value='" + aData['nilaiSpd'] + "' />"
                var sppsebelumtext = "<input type='text' name='nilaiSppSebelum" + index + "' id='nilaiSppSebelum" + index + "'  class='inputmoney'  readonly='true'   value='" + aData['nilaiSppSebelum'] + "' />"
                var sppsisatext = "<input type='text' name='nilaiSppSisa" +index + "' id='nilaiSppSisa" + index + "'  class='inputmoney'  readonly='true'   value='" + aData['nilaiSppSisa'] + "' />"
                var nilaispptext = "<input type='text' name='nilaispp" + index + "' id='nilaispp" + index + "'  class='inputmoney'  " + readonly + "    onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'   value='" + aData['nilaiSpp'] + "'   />"


                /*
                var idspd = "<input type='hidden' id='idspd" + aData['idBiaya'] + "'   name='idspd" + aData['idBiaya'] + "'  value='" + aData['idSpd'] + "'  />";
                var idakun = "<input type='hidden' id='idakun" + aData['idBiaya'] + "'   name='idakun" + aData['idBiaya'] + "'  value='" + aData['akun']['idAkun'] + "'  />";
                var noSpd = "<input type='hidden' name='noSpd'     kode='noSpd' value='" + aData['noSpd'] + "' />";
                var isceked = aData['nilaiSpp'] > 0 ? 'checked' : '';
                var readonly = 'readonly'
                if (isceked === 'checked') {
                    readonly = '';
                }
                
                var stat = aData['statusSpd'];
                if ( stat != 'P' )
                {
                    var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBiaya'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + aData['idBiaya'] + ") " + isceked + " />";
                }else
                {
                    var inputcek = '-';
                }
                
                
                var namaakun = "<textarea name='namaAkun'  style='border:none;margin:0;width:99%;'  kode='namaAkun'  >" + aData['akun']['namaAkun'] + "</textarea>"
                //var nilaispd = accounting.formatNumber(aData['nilaiSpd'], 2, '.', ",");
                var nilaispdtext = "<input type='text' name='nilaispd" + aData['idBiaya'] + "' id='nilaispd" + aData['idBiaya'] + "'  class='inputmoney'  readonly='true'   value='" + aData['nilaiSpd'] + "' />"
                //var sppsebelum = accounting.formatNumber(aData['nilaiSppSebelum'], 2, '.', ",");
                var sppsebelumtext = "<input type='text' name='nilaiSppSebelum" + aData['idBiaya'] + "' id='nilaiSppSebelum" + aData['idBiaya'] + "'  class='inputmoney'  readonly='true'   value='" + aData['nilaiSppSebelum'] + "' />"
                //var sppsisa = accounting.formatNumber(aData['nilaiSppSisa'], 2, '.', ",");
                var sppsisatext = "<input type='text' name='nilaiSppSisa" + aData['idBiaya'] + "' id='nilaiSppSisa" + aData['idBiaya'] + "'  class='inputmoney'  readonly='true'   value='" + aData['nilaiSppSisa'] + "' />"
                //var nilaispp = accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var nilaispptext = "<input type='text' name='nilaispp" + aData['idBiaya'] + "' id='nilaispp" + aData['idBiaya'] + "'  class='inputmoney'  " + readonly + "    onkeyup='pasangvalidatebesarperfield(" + aData['idBiaya'] + ");hitungnilaispp()'   value='" + aData['nilaiSpp'] + "'   />"
                */
               
                $('td:eq(0)', nRow).html(index + idspd + idakun+idbiaya);
                $('td:eq(3)', nRow).html(namaakun);
                $('td:eq(4)', nRow).html(nilaispdtext);
                $('td:eq(5)', nRow).html(sppsebelumtext);
                $('td:eq(6)', nRow).html(sppsisatext);
                $('td:eq(7)', nRow).html(nilaispptext);
                $('td:eq(8)', nRow).html(inputcek);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBiaya", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noSpd", "bSortable": true, "sWidth": "8%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, "sWidth": "17%"},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "nilaiSppSebelum", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "nilaiSppSisa", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "idBiaya", "bSortable": false, sClass: "center", "sWidth": "4%"}
            ]
        });


    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    getbanyakspdrinci( );
}
function enablerow(obj, idbiaya) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbiaya);
}
function setdisabled(param, idbtl) {
    $("#nilaispp" + idbtl).attr("readonly", param);
    //$("#nilaispd" + idbtl).attr("readonly", param);
    //$("#nilaiSppSisa" + idbtl).attr("readonly", param);
    if (param == false) {
        var nilaispp = accounting.unformat($("#nilaispp" + idbtl).val(), ",");
        var nilaispd = accounting.unformat($("#nilaispd" + idbtl).val(), ",");
        var sppsisa = accounting.unformat($("#nilaiSppSisa" + idbtl).val(), ",");
        var nilaiisian = accounting.formatNumber((nilaispp == 0 ? sppsisa : nilaispp), 2, '.', ",");
        $("#nilaispp" + idbtl).val(nilaiisian);
    }
    hitungnilaispp();
}
function pasangvalidatebesarperfield(idbiaya) {
    //console.log("idbiaya = "+idbiaya);
   var nilaispp = accounting.unformat($("#nilaispp" + idbiaya).val(), ",");
    var nilaispd = accounting.unformat($("#nilaispd" + idbiaya).val(), ",");
    var nilaisisa = accounting.unformat($("#nilaiSppSisa" + idbiaya).val(), ",");
    var strtotalAngaran = $("#totalAngaran").val();
   // console.log("nilaiSppSisa1 = "+$("#nilaiSppSisa1").val());
    //console.log("nilaispp index = "+nilaispp);
    //var totalAngaran =  accounting.unformat($("#totalAngaran" + idbiaya).val(), ",");
    //var totalAngaran = strtotalAngaran.split('.').join('');
    //var totalspp = accounting.unformat($("#totalspp" + idbiaya).val(), ",");
    //var status = nilaispp <= nilaispd && nilaispp < totalAngaran && totalspp <= totalAngaran;
    var status =nilaispp <= nilaisisa ;
    //console.log((nilaispp < totalAngaran) + "  " + status + "  " + nilaispp + "  " + nilaispd + " " + totalAngaran);
    if (!status) {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari nilai Sisa SPP", function() {
            //$("#nilaispp" + idbiaya).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
            //$("#nilaispp" + idbiaya).focus();
            $('#nilaispp' + idbiaya).autoNumeric('set', nilaisisa);
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
    });
    $("#totalspp").val(accounting.formatNumber(total, 2, '.', ","));


}

function gettotalspddanspp( ) {
    $.getJSON(getbasepath() + "/sppbiaya/json/gettotalspddanspp",
            {
                tahunAnggaran: $("#tahun").val(),
                idskpd: $("#idskpd").val(),
                idspp: $("#id").val(),
                spdno: $("#nospdfilter").val()
            },
    function (result) {
        $('#totalspd').val(accounting.formatNumber(  result.V_SPD, 2, '.', ","));
        $('#totalspp').val(accounting.formatNumber( result.V_SPP, 2, '.', ",") );
    });

}
 
function getDataBankDki() {
    var kodebank = $('#kodeBankTransfer').val();
    var norek = $('#nomorRekBank').val();
    norek = norek.replace(/[-., *+?^${}()/|[\]\\]/g, "");
    
    $("#refsppbiaya").valid();
    //console.log("getDataBankDki()+ kode bank == "+kodebank);
    
    $('#dkinamabank').val("");
    $('#dkikodebank').val("");
    $('#dkinorek').val("");
    $('#dkialamat').val("");
    $('#dkinama').val("");
    $('#dkinpwp').val("");
    
    //if ((kodebank == "111") && (norek !== "")) {
    if (norek !== "") {
        //var varurl = getbasepath() + "/postdata/json/kirimpostdata";
        var varurl = getbasepath() + "/postdata/json/ceknorek";

        var dataac = [];

        var datajour = {
            kodebank: kodebank,
            norek: norek
        };
        dataac = datajour;

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: varurl,
            data: JSON.stringify(dataac),
            success: function(data) {
                console.log(data);

                if (kodebank == '111') {
                    $('#dkinamabank').val(data.namabank);
                    $('#dkikodebank').val(data.kodebank);
                    $('#dkinorek').val(data.norek);
                    $('#dkialamat').val(data.alamat);
                    $('#dkinama').val(data.nama);
                    $('#dkinpwp').val(data.npwp);

                } else {
                    $('#dkinamabank').val(data["bank"]);
                    $('#dkikodebank').val(data["code"]);
                    $('#dkinorek').val(data["account"]);
                    $('#dkinama').val(data["name"].trim());
                    $('#dkialamat').val("");
                    $('#dkinpwp').val("");
                    response76 = data["response"];
                }

            },
            error: function(xhr) {
                console.error(xhr);
            }
        }).always(function(data) {
            $('#buttoninduk').attr("disabled", false);
            //$("#spdBTLMasterform").valid();
        }); 
    }
        
}

function cek() {
    if ($("#kodeBankTransfer").val() !== "001") {
        if ($("#nomorRekBank").val() !== $("#dkinorek").val()) {
            bootbox.alert("Cek Koneksi ke Bank DKI");
            return false;

        } else if ($("#dkinama").val() == "GENERAL ERROR" || $("#dkinorek").val() == "GENERAL ERROR") {
            bootbox.alert("Cek Koneksi ke Bank DKI");
            return false;

        } else if ($("#dkinama").val() == "rekening Tidak Ada Pada Master/rekening Tidak Aktif") {
            bootbox.alert("Rekening Tidak Ada Pada Master/Rekening Tidak Aktif");
            return false;

        } else if ($("#dkinama").val().trim() !== $("#namaYayasan").val()) {
            bootbox.alert("Nama Penerima Harus Sama dengan Nama dari Bank DKI");
            return false;

        } else if (response76 !== "00") {
            bootbox.alert("Data Nasabah Tidak Terdaftar");
            return false;

        } else {
            return true;
        }

    } else {
       return true;
    }

}
