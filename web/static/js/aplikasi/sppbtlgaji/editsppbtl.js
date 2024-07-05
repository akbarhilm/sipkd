$(document).ready(function () {
    //getDataBankDki();
    idskpdCheck();
    gridspprinci();
    
    var kodesimpeg = $('#kodesimpeg').val();
    var kode = kodesimpeg == 4 ? 'PPh' : kodesimpeg == 3 ? 'Transport' : kodesimpeg == 2 ? 'TKD' : kodesimpeg == 1 ? 'Gaji' : '-- Pilih --';
    $('#textkodesimpeg').val(kode);
    //var namaYayasan = $("#namaYayasan")
    //var  namaPenerima = $("#namaPenerima")
    //var alamatBantuan = $("alamatBantuan")
    
    $('#ketgaji').val($('#uraian').val());
    $('#textjumkotsimpeg').val($('#jumkotsimpeg').val());

    console.log("jumkot = "+$('#jumkotsimpeg').val());

    

});

// var global
var response76 = "";
var banyakBarisGrid=0;
var jumlahkotorsimpeg=0;

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
    //$('#dkinpwp').val("");

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
            success: function (data) {
                console.log(data);

                if (kodebank == '111') {
                    $('#dkinamabank').val(data.namabank);
                    $('#dkikodebank').val(data.kodebank);
                    $('#dkinorek').val(data.norek);
                    $('#dkialamat').val(data.alamat);
                    $('#dkinama').val(data.nama);
                    //$('#dkinpwp').val(data.npwp);

                } else {
                    $('#dkinamabank').val(data["bank"]);
                    $('#dkikodebank').val(data["code"]);
                    $('#dkinorek').val(data["account"]);
                    $('#dkinama').val(data["name"]);
                    $('#dkialamat').val("");
                    //$('#dkinpwp').val("");
                }

                if (data.nama == "Rekening Tidak Ada Pada Master/Rekening Tidak Aktif" || data.nama == "") {
                    //$('#buttoninduk').attr("disabled", true);
                    document.getElementById("labelCIF111").style.display = "block";

                } else {
                    $('#buttoninduk').attr("disabled", false);
                    document.getElementById("labelCIF111").style.display = "none";
                }

                if (idskpd == "761") {
                    if (data.nama !== namaYayasan) {
                        //$('#buttoninduk').attr("disabled", true);
                        document.getElementById("labelCekNama").style.display = "block";

                    } else {
                        $('#buttoninduk').attr("disabled", false);
                        document.getElementById("labelCekNama").style.display = "none";
                    }
                }

            },
            error: function (xhr) {
                console.error(xhr);
                //$('#buttoninduk').attr("disabled", true);
            }
        }).always(function (data) {
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
        if (idskpd == "761") {
            if (namadki !== namaYayasan) {
                //$('#buttoninduk').attr("disabled", true);
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

function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        //window.location.replace(getbasepath() + "/btl/indexbtl/" + idskpd);
        window.location.replace(getbasepath() + "/btlgaji/indexbtl");
    } else {
        window.location.replace(getbasepath() + "/btlgaji/indexbtl");
    }

}

function gridspprinci() {
    if($('#kodesimpeg').val() != 0){
        document.getElementById("gridsppbtlgaji").style.display = "block";
    var urljson = getbasepath() + "/btlgaji/json/getlistrincibtl";
    if (typeof myTable == 'undefined' ) {
        myTable = $('#spprincitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "autoWidth": false,
            "bInfo": true,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 25,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function () {
                //$("#spprincitable :input").not("input[type='checkbox']").attr("readonly", "readonly");
                //$(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15, allowNegative: true, insertPlusSign: 'true'});
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspp", value: $('#id').val() == '' || $('#id').val() == null ? 0 : $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()},
                {name: "kodesimpeg", value: $('#kodesimpeg').val()}
                );
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) {
                var pageTotal = 0;
                var spptotal = 0;
                if (aaData.length > 0) {
                    for (var i = iStart; i < iEnd; i++) {
                        pageTotal += parseFloat(aaData[i]['nilaiSpd']);
                        spptotal += parseFloat(aaData[i]['nilaiSpp']);
                    }
                }

                //$("#totalspd").val(accounting.formatNumber(pageTotal, 2, '.', ","))
                $("#totalspp").val(accounting.formatNumber(spptotal, 2, '.', ","))
                //banyakBarisGrid = aaData.length;
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
                //console.log("index = " + index);
                banyakBarisGrid = index;
                $('#banyakrinci').val(banyakBarisGrid);
                var idspd = "<input type='hidden' id='idspd" + index + "'   name='idspd" + index + "'  value='" + aData['idSpd'] + "'  />";
                var idakun = "<input type='hidden' id='idakun" + index + "'   name='idakun" + index + "'  value='" + aData['akun']['idAkun'] + "'  />";
                var isceked = aData['nilaiSpp'] != 0 ? 'checked' : '';
                var isreadonly = 'readonly';
                if (isceked === 'checked') {
                    isreadonly = '';
                }
                //console.log(index+" = "+aData['statusSpd']);
                var statSpd = "<input type='hidden' id='statspd" + index + "'   name='statspd" + index + "'  value='" + aData['statusSpd'] + "'  />";
                var idbtl = "<input type='hidden' id='idbtl" + index + "'   name='idbtl" + index + "'  value='" + aData['idBtl'] + "'  />";

                //var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBtl'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";
                var cek = aData['nilaiSpp'] > 0 ? 1 : 0;
                
                var pilih = "<input type='hidden' id='pilih" + index + "'   name='pilih" + index + "' value='"+ cek +"' />";
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "'  " + isceked + "  onchange=enablerow(this," + index + ") />";
                var kodeakun = "<input type='text' name='kodeAkun'  style='border:none;margin:0;width:99%;'  id='kodeAkun" + index + "' value='" + aData['akun']['kodeAkun'] + "' readonly='true'/>"
                var namaakun = "<input type='text' name='namaAkun'  style='border:none;margin:0;width:99%;'  kode='namaAkun' value='" + aData['akun']['namaAkun'] + "' readonly='true' />"
                var nilaispd = aData['nilaiSpd'];//accounting.formatNumber(aData['nilaiSpd'], 2, '.', ",");
                var nilaispdtext = "<input type='text' name='nilaispd" + index + "' id='nilaispd" + index + "'  class='inputmoney'  readonly='true'   value='" + nilaispd + "' />"
                var sppsebelum = aData['nilaiSppSebelum'];//accounting.formatNumber(aData['nilaiSppSebelum'], 2, '.', ",");
                var sppsebelumtext = "<input type='text' name='nilaiSppSebelum" + index + "' id='nilaiSppSebelum" + index + "'  class='inputmoney'  readonly='true'   value='" + sppsebelum + "' />"
                var sppsisa = aData['nilaiSppSisa'];//accounting.formatNumber(aData['nilaiSppSisa'], 2, '.', ",");
                var sppsisatext = "<input type='text' name='nilaiSppSisa" + index + "' id='nilaiSppSisa" + index + "'  class='inputmoney'  readonly='true'   value='" + sppsisa + "' />"
                var nilaispp = aData['nilaiSpp'];//accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                //var nilaispptext = "<input type='text' name='nilaispp" + index + "' id='nilaispp" + index + "'  class='inputmoney'  " + readonly + "    onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'   value='" + nilaispp + "'   />"
                var nilaispptext = "<input type='text' name='nilaispp" + index + "' id='nilaispp" + index + "'  class='inputmoney'  "+isreadonly+"     value='" + nilaispp + "' onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'  />"

                $('td:eq(0)', nRow).html(index + idspd + idakun + idbtl + statSpd + pilih);
                $('td:eq(2)', nRow).html(kodeakun);
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
                {"mDataProp": "noSpd", "bSortable": true, "sWidth": "8%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, "sWidth": "17%"},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "nilaiSppSebelum", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "nilaiSppSisa", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "idBtl", "bSortable": false, sClass: "center", "sWidth": "4%"}
            ]
        });
        
        //getNilaiSPP();
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    }
    else {
        $('#idlist').val(-1);
        $('#ketgaji').val("");
        $('#uraian').val("");
        document.getElementById("gridsppbtlgaji").style.display = "none";
    }
    
    //getbanyakspdrinci( );
    //getNilaiSPP();
}

function enablerow(obj, idbtl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbtl)
}
function setdisabled(param, index) {
    $("#nilaispp" + index).attr("readonly", param);
    // $("#nilaispd" + idbtl).attr("readonly", param);

    if (param == false) { //check
        $("#pilih" + index).val('1');
    } else { //uncheck
        $("#pilih" + index).val('0');
    }
    hitungnilaispp();
}

function getNilaiSPP(){
    var idlist = $('#idlist').val();
    var kdsimpeg = $('#kodesimpeg').val();
    $.getJSON(getbasepath() + "/btlgaji/json/getNilaiSPP/" + idlist + "/" + kdsimpeg,
            function (result) {
                jumlahkotorsimpeg = result[0].JUMKOT;
                $('#jumkotsimpeg').val(jumlahkotorsimpeg);
                //console.log(result.length);
                //console.log(result[0]);
                //console.log("masuk getNilaiSPP = "+banyakBarisGrid);
                //console.log(result[0].NILAISPP);
                var i;
                for(i=0;i<result.length;i++){
                    inputNilaiSPP(result[i].KODEAKUN, result[i].NILAISPP);
                }
            });
}

function inputNilaiSPP(kodeakun, nilaiSPP) {
    var i;
    var nilai = nilaiSPP;
    //console.log("masuk inputNilaiSPP = "+banyakBarisGrid);
    for (i = 1; i <= banyakBarisGrid; i++) {
        //console.log("masuk inputNilaiSPP looping ke == "+i);
        //console.log("kode akun ke-"+i+" == "+$("#kodeAkun"+i).val());
        
        if (kodeakun == $("#kodeAkun" + i).val() ) {
            //console.log("masuk kodeakunsama looping ke == " + i);
            if (parseFloat(nilai) == 0 || $("#statspd" + i).val() == 'P') {
                //console.log("status spd = "+$("#statspd" + i).val());
                break;
            }
            else if (parseFloat(nilai) <= parseFloat(accounting.unformat($("#nilaiSppSisa" + i).val(), ","))) {
                //console.log("NILAI AKUN " + kodeakun + " == " + parseFloat(nilai));
                document.getElementById("cekpilih" + i).checked = true;
                $("#pilih" + i).val("1");
                $("#nilaispp" + i).val(accounting.formatNumber(nilai, 2, '.', ",")).change();
                break;
            }
            else {
                if (parseFloat(accounting.unformat($("#nilaiSppSisa" + i).val(), ",")) > 0) {
                    document.getElementById("cekpilih" + i).checked = true;
                    nilai = parseFloat(nilai) - parseFloat(accounting.unformat($("#nilaiSppSisa" + i).val(), ","));
                    //console.log("NILAI AKUN == " + nilai);
                    $("#pilih" + i).val("1");
                    $("#nilaispp" + i).val($("#nilaiSppSisa" + i).val()).change();
                }
            }
        }
    }
}

function hitungnilaispp() {
    console.log("hitung total");
    var searchIDs = $("#spprincitable input:checkbox:checked").not("#pilihall").map(function () {
        return $(this).val();
    }).get();
    var total = 0;
    console.log("serachID = "+searchIDs);
    $.each(searchIDs, function (idx, data) {
        console.log("hitung total data-"+ idx +" == "+ parseFloat(accounting.unformat($("#nilaispp" + data).val(), ",")));
        total += parseFloat(accounting.unformat($("#nilaispp" + data).val(), ","));
    })
    
    $("#totalspp").val(accounting.formatNumber(total, 2, '.', ","));
    
    //console.log("jumkot = " + jumlahkotorsimpeg);


}

function pasangvalidatebesarperfield(index) {
    var nilaispp = accounting.unformat($("#nilaispp" + index).val(), ",");
    //var nilaispd = accounting.unformat($("#nilaispd" + idbl).val(), ","); edit 14 Jan 2016 by zainab : validasi ke sisa spp, bukan ke SPD
    var nilaisisa = accounting.unformat($("#nilaiSppSisa" + index).val(), ",");
    var status = nilaispp >= nilaisisa ? false : true;
    //console.log("nilaisisa = "+nilaisisa);
    console.log("nilaispp = "+nilaispp);
    // console.log(status+"  "+nilaispp+"  "+nilaispd);
    if (!status) {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari nilai SPP Sisa", function () {
            console.log("nilaisisa = "+nilaisisa);
            $('#nilaispp' + index).autoNumeric('set', 0);// edit 9 Nov 2016 by zainab : format input money diganti
            //$("#nilaispp" + idbl).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
            $("#nilaispp" + index).focus();
            hitungnilaispp();
        });

    } else {
        return true;
    }
}