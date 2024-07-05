$(document).ready(function () {
    getDataBankDki();
    gridspprinci();
    idskpdCheck();
    var namaYayasan = $("#namaYayasan")
    var  namaPenerima = $("#namaPenerima")
    var alamatBantuan = $("alamatBantuan")
    if ($("#idskpd").val()) {
        // getpagudanspd($("#idskpd").val())
    }
    $('#pilihall').click(function () {
        $(':checkbox').prop('checked', this.checked);
        cekall(this);
        hitungnilaispp();
    });


    $("#nospdfilter").keyup(function () {
        var panjang = $(this).val().length;
        if (panjang > 1) {
            gridspprinci();
        }
    });

});

function cekbulanlisting(value) {
    var bulan = value.substr(0, 2);
    var tahun = value.substring(3);

    console.log("ruleBulanListing value = " + value);
    console.log("value tahun = " + value.substring(3));
    //console.log("value tahun angg = " + (tahunangg-1));

    if (value == "") {
        bootbox.alert("Pengisian Data Harus Benar");
        $("#bulanListing").val("10/2017");
    } else {
        if (tahun <= 2017) {
            if (tahun == 2017 && bulan > 10) {
                bootbox.alert("Bulan Listing Tidak Boleh Lebih Besar Dari 10/2017");
                $("#bulanListing").val("10/2017");
                //$('#buttoninduk').attr("disabled", true);
            } else {
                //return true;
            }
        } else {
            bootbox.alert("Bulan Listing Tidak Boleh Lebih Besar Dari 10/2017");
            $("#bulanListing").val("10/2017");
            //$('#buttoninduk').attr("disabled", true);
        }
    }

}


function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/btl/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function (result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });

}
function getbanyakspdrinci( ) {
    $.getJSON(getbasepath() + "/btl/json/getlistspdbtlbanyak", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val()},
    function (result) {
        $('#banyakrinci').val(result);
    });

}
function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/btl/indexbtl/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/btl/indexbtl");
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
    var urljson = getbasepath() + "/btl/json/getlistspdbl";
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
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function () {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','}); // edit 9 Nov 2016 by zainab : format input money diganti
                //$(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15, allowNegative: true, insertPlusSign: 'true'});
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspp", value: $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()},
                {name: "nospd", value: $('#nospdfilter').val()}
                );
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) {
                var pageTotal = 0;
                var spptotal = 0;
                if (aaData.length > 0) {
                    try {
                        for (var i = iStart; i < iEnd; i++) {
                            pageTotal += parseFloat(aaData[i]['nilaiSpd']);
                            spptotal += parseFloat(aaData[i]['nilaiSpp']);
                        }
                    } catch (e) {

                    }
                }

                //$("#totalspd").val(accounting.formatNumber(pageTotal, 2, '.', ","))
                $("#totalspp").val(accounting.formatNumber(spptotal, 2, '.', ","))

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
                var idspd = "<input type='hidden' id='idspd" + index + "'   name='idspd" + index + "'  value='" + aData['idSpd'] + "'  />";
                var idakun = "<input type='hidden' id='idakun" + index + "'   name='idakun" + index + "'  value='" + aData['akun']['idAkun'] + "'  />";
                var noSpd = "<input type='hidden' name='noSpd'     kode='noSpd' value='" + aData['noSpd'] + "' />";
                var isceked = aData['nilaiSpp'] != 0 ? 'checked' : '';
                var readonly = 'readonly';
                var inputcek;
                if (isceked === 'checked') {
                    readonly = '';
                }
                var idbtl = "<input type='hidden' id='idbtl" + index + "'   name='idbtl" + index + "'  value='" + aData['idBtl'] + "'  />";
                var stat = aData['statusSpd'];
                if ( stat != 'P' )
                {
                    inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";
                }else
                {
                    inputcek = '-'
                }
                
                var namaakun = "<textarea name='namaAkun'  style='border:none;margin:0;width:99%;'  kode='namaAkun'  >" + aData['akun']['namaAkun'] + "</textarea>";
                // edit 9 Nov 2016 by zainab : format input money diganti
                var nilaispd = aData['nilaiSpd']; //accounting.formatNumber(aData['nilaiSpd'], 2, '.', ",");
                var nilaispdtext = "<input type='text' name='nilaispd" + index + "' id='nilaispd" + index + "'  class='inputmoney'  readonly='true'   value='" + nilaispd + "' />"
                
                var sppsebelum = aData['nilaiSppSebelum']; //accounting.formatNumber(aData['nilaiSppSebelum'], 2, '.', ",");
                var sppsebelumtext = "<input type='text' name='nilaiSppSebelum" + index + "' id='nilaiSppSebelum" + index + "'  class='inputmoney'  readonly='true'   value='" + sppsebelum + "' />"
                
                var sppsisa = aData['nilaiSppSisa']; //accounting.formatNumber(aData['nilaiSppSisa'], 2, '.', ",");
                var sppsisatext = "<input type='text' name='nilaiSppSisa" + index + "' id='nilaiSppSisa" + index + "'  class='inputmoney'  readonly='true'   value='" + sppsisa + "' />"
                
                var nilaispp = aData['nilaiSpp'] //accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var nilaispptext = "<input type='text' name='nilaispp" + index + "' id='nilaispp" + index + "'  class='inputmoney'  " + readonly + "    onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'   value='" + nilaispp + "'   />"

                $('td:eq(0)', nRow).html(index + idspd + idakun + idbtl);
                $('td:eq(3)', nRow).html(namaakun);
                $('td:eq(4)', nRow).html(nilaispdtext);
                $('td:eq(5)', nRow).html(sppsebelumtext);
                $('td:eq(6)', nRow).html(sppsisatext);
                $('td:eq(7)', nRow).html(nilaispptext);
                $('td:eq(8)', nRow).html(inputcek);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBtl", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noSpd", "bSortable": true, "sWidth": "4%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, "sWidth": "17%"},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "nilaiSppSebelum", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "nilaiSppSisa", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "14%"},
                {"mDataProp": "idBtl", "bSortable": false, sClass: "center", "sWidth": "4%"}
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
function enablerow(obj, idbtl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbtl)
}
function setdisabled(param, idbtl) {
    $("#nilaispp" + idbtl).attr("readonly", param);
   // $("#nilaispd" + idbtl).attr("readonly", param);
   // $("#nilaiSppSisa" + idbtl).attr("readonly", param);
   
    if (param == false) {
        var nilaispp = accounting.unformat($("#nilaispp" + idbtl).val(), ",");
        var nilaispd = accounting.unformat($("#nilaispd" + idbtl).val(), ",");
        var sppsisa = accounting.unformat($("#nilaiSppSisa" + idbtl).val(), ",");
        var nilaiisian = accounting.formatNumber((nilaispp == 0 ? sppsisa : nilaispp), 2, '.', ",");
        $("#nilaispp" + idbtl).val(nilaiisian)
    }
    
    hitungnilaispp();
}

function pasangvalidatebesarperfield(idbl) {
    var nilaispp = accounting.unformat($("#nilaispp" + idbl).val(), ",");
    //var nilaispd = accounting.unformat($("#nilaispd" + idbl).val(), ","); edit 14 Jan 2016 by zainab : validasi ke sisa spp, bukan ke SPD
    var nilaisisa = accounting.unformat($("#nilaiSppSisa" + idbl).val(), ",");
    var status = nilaispp >= nilaisisa ? false : true;
    //console.log("nilaisisa = "+nilaisisa);
    console.log("nilaispp = "+nilaispp);
    // console.log(status+"  "+nilaispp+"  "+nilaispd);
    if (!status) {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari nilai SPP Sisa", function () {
            console.log("nilaisisa = "+nilaisisa);
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
    var searchIDs = $("#spprincitable input:checkbox:checked").not("#pilihall").map(function () {
        return $(this).val();
    }).get();
    var total = 0;
    $.each(searchIDs, function (idx, data) {
        total += parseFloat(accounting.unformat($("#nilaispp" + data).val(), ","));
    })
    $("#totalspp").val(accounting.formatNumber(total, 2, '.', ","));


}
 
 function getDataBankDki() {
    var idskpd = $('#idskpd').val();
    var namaYayasan = $('#namaYayasan').val();
    
    var kodebank = $('#kodeBankTransfer').val();
    var norek = $('#nomorRekBank').val();
    norek = norek.replace(/[-., *+?^${}()/|[\]\\]/g, "");
    //$("#refsppbtl").valid();
    //console.log("getDataBankDki()+ norek replace == "+norek);
    
    $('#dkinamabank').val("");
    $('#dkikodebank').val("");
    $('#dkinorek').val("");
    $('#dkialamat').val("");
    $('#dkinama').val("");
    $('#dkinpwp').val("");
    
    if ((kodebank == "111") && (norek !== "")) {
        var varurl = getbasepath() + "/postdata/json/kirimpostdata";

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

                $('#dkinamabank').val(data.namabank);
                $('#dkikodebank').val(data.kodebank);
                $('#dkinorek').val(data.norek);
                $('#dkialamat').val(data.alamat);
                $('#dkinama').val(data.nama);
                $('#dkinpwp').val(data.npwp);
                
                if (data.nama == "Rekening Tidak Ada Pada Master/Rekening Tidak Aktif" || data.nama == "") {
                    $('#buttoninduk').attr("disabled", true);
                    document.getElementById("labelCIF111").style.display = "block";
                    
                } else {
                    $('#buttoninduk').attr("disabled", false);
                    document.getElementById("labelCIF111").style.display = "none";
                }

                if (idskpd == "761" || idskpd == "1234") {
                    if (data.nama !== namaYayasan) {
                        $('#buttoninduk').attr("disabled", true);
                        document.getElementById("labelCekNama").style.display = "block";
                    } else {
                        $('#buttoninduk').attr("disabled", false);
                        document.getElementById("labelCekNama").style.display = "none";
                    }
                }
            },
            error: function(xhr) {
                $('#buttoninduk').attr("disabled", true);
                console.error(xhr);
            }
        }).always(function(data) {
            //$('#buttoninduk').attr("disabled", false);
            //$("#spdBTLMasterform").valid();
        }); 
    }
        
}

function ceknamapenerima() {
    var idskpd = $('#idskpd').val();
    var namaYayasan = $('#namaYayasan').val();
    var kodebank = $('#kodeBankTransfer').val();
    var namadki = $('#dkinama').val();

    if ((kodebank == "111")) {
        if (idskpd == "761" || idskpd == "1234") {
            if (namadki !== namaYayasan) {
                $('#buttoninduk').attr("disabled", true);
                document.getElementById("labelCekNama").style.display = "block";

            } else {
                $('#buttoninduk').attr("disabled", false);
                document.getElementById("labelCekNama").style.display = "none";
            }
        }
    } else {
        $('#buttoninduk').attr("disabled", false);
        document.getElementById("labelCekNama").style.display = "none";
    }
}
