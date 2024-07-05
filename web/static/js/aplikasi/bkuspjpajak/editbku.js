$(document).ready(function() {
    setPanel();
    getBulanByRekap();
    gridspj();
    /*getNilaiSisaSpj();
     getNilaiValidasiSisaSpj();
     $("#sumspj").val(0);
     $("#sumpajak").val(0);
     $("#totalspjhidden").val(0);
     */
});
// global variable
var jumbarisspj = 0;
var totalpajakawal = 0;
var banyakTutupMax, kodeTutupMax, bulanTutupMax, saldoAwal, ketSaldoAwal;
function setPanel() {
    var beban = $('#beban').val();
    var grid = document.getElementById('spjtable');
    if (beban == "TU") {
        grid.rows[0].cells[4].innerText = 'Nilai SPP TU';
        grid.rows[0].cells[6].innerText = 'Sisa SPP TU';
        document.getElementById('textlabelsisaspj').innerHTML = 'Sisa Uang Persediaan TU (Kas SKPD) :';
        document.getElementById('txtlabelnilaitu').innerHTML = 'Nilai BKU TU (4) :';
        $('#bebanspj').val("TU");
    } else if (beban == "UP") {
        grid.rows[0].cells[4].innerText = 'Nilai LS (BAST)';
        grid.rows[0].cells[6].innerText = 'Sisa SPD';
        document.getElementById('textlabelsisaspj').innerHTML = 'Sisa Uang Persediaan UP/GU (Kas SKPD) :';
        document.getElementById('txtlabelnilaitu').innerHTML = 'Nilai SPP TU (4) :';
        $('#bebanspj').val("UP/GU");
    }
}

function gridspj() {
    totalpajakawal = 0;
//jumbarisspj = 0;
    var beban = $("#beban").val();
    //console.log("BEBAN SPJ = "+beban);
    if (beban == "UP" || beban == "TU") {
        if (typeof myTable == 'undefined') {
            myTable = $('#spjtable').dataTable({
                "bPaginate": true,
                "sPaginationType": "bootstrap",
                "bJQueryUI": false,
                "bProcessing": true,
                "bServerSide": true,
                "bInfo": true,
                "bScrollCollapse": true,
                "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
                "iDisplayLength": 100,
                //"sScrollY": ($(window).height() * 108 / 100),
                "bFilter": false,
                "sAjaxSource": getbasepath() + "/bkuspjpajak/json/listbkuspj",
                "aaSorting": [[1, "asc"]],
                "fnDrawCallback": function() {
                    $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
                },
                "fnServerParams": function(aoData) {
                    aoData.push(
                            {name: "tahun", value: $("#tahun").val()},
                    {name: "idskpd", value: $("#idskpd").val()},
                    {name: "nobku", value: $("#noBKU").val()},
                    {name: "beban", value: $("#beban").val()},
                    {name: "idkegiatan", value: $("#idKegiatan").val()},
                    {name: "nobukti", value: $("#noBukti").val()}
                    );
                },
                "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

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
                    var isceked = aData['nilaiBkuInput'] > 0 ? 'checked' : '';
                    var readonlyinput = aData['nilaiBkuInput'] > 0 ? "" : "readonly";
                    var nilaipenanda = aData['nilaiBkuInput'] > 0 ? 1 : 0;
                    var total = parseInt($("#totalspjhidden").val());
                    var pilihpajak;

                    if (parseInt(aData['nilaiBkuInput']) > 0) {
                        total = total + parseInt(aData['nilaiBkuInput']);
                        $("#totalspjhidden").val(total);
                        $("#sumspj").val(accounting.formatNumber(total, 2, '.', ","));
                        //console.log("2 total = "+total);
                        pilihpajak = "<a id='inputpajak" + index + "' onclick='setketpajak(" + index + "," + aData['idBas'] + ")' class='fancybox fancybox.iframe' href='" + getbasepath() + "/bkuspjpajak/listpajak?target='_top'' title='Input Pajak SPJ'  ><i class='icon-edit'>";
                    } else {
                        pilihpajak = "<a id='inputpajak" + index + "' >";
                    }

                    if (parseInt(aData['nilaiPajak']) > 0) {
                        totalpajakawal = totalpajakawal + parseInt(aData['nilaiPajak']);
                        $("#sumpajak").val(accounting.formatNumber(totalpajakawal, 2, '.', ","));
                        $("#totalpajakhidden").val(totalpajakawal);
                    }

                    var penanda = "<input type='hidden' id='penanda" + index + "' value='" + nilaipenanda + "' />";
                    var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                    var nilaiAngg = "<input type='text' name='nilaiAnggaran" + index + "' id='nilaiAnggaran" + index + "'  class='inputmoney'  value='" + aData['nilaiAnggaran'] + "' readOnly='true' />";
                    var nilaisp2d = "<input type='text' name='nilaisp2d" + index + "' id='nilaisp2d" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSp2d'] + "' readOnly='true' />";
                    var nilaisebelum = "<input type='text' name='nilaisebelum" + index + "' id='nilaisebelum" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSebelum'] + "' readOnly='true' />";
                    var nilaisisa = "<input type='text' name='nilaisisa" + index + "' id='nilaisisa" + index + "'  class='inputmoney'  value='" + aData['nilaiSisa'] + "' readOnly='true' />";
                    //var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' " + readonlyinput + "  onkeyup='pasangvalidatebesarperfield(" + index + ")' onchange='pasangvalidatebesarperfield(" + index + ")'  />";
                    var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' " + readonlyinput + " onkeyup='pasangvalidatebesarperfield(" + index + ")' onchange='pasangvalidatebesarperfield(" + index + "), validasipajak(" + index + ")' />";
                    var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";
                    var namaakun = "<textarea id='akunnama" + index + "'readonly style='border:none;margin:0;width:170px;'>" + aData['namaakun'] + "</textarea>";
                    var textnamaakun = "<input type='hidden' id='namaakun" + index + "' value='" + aData['namaakun'] + "' />";
                    var textkodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                    var pajakunformat = "<input type='hidden' id='pajakunformat" + index + "' value='" + aData['nilaiPajak'] + "' />";
                    var nilaip1 = "<input type='hidden' id='nilaip1" + index + "' value='0' />";
                    var nilaip2 = "<input type='hidden' id='nilaip2" + index + "' value='0' />";
                    var nilaip3 = "<input type='hidden' id='nilaip3" + index + "' value='0' />";
                    var nilaip4 = "<input type='hidden' id='nilaip4" + index + "' value='0' />";
                    var nilaip5 = "<input type='hidden' id='nilaip5" + index + "' value='0' />";
                    var nilaip6 = "<input type='hidden' id='nilaip6" + index + "' value='0' />";
                    var nilaipajak = "<input type='text' name='totalpajak" + index + "' id='totalpajak" + index + "'  class='inputmoney' value='" + aData['nilaiPajak'] + "' readOnly='true' onchange='hitungtoralpajak()' onclick='setnilaipajak()' />";
                    var nobkup1 = "<input type='hidden' id='nobkup1" + index + "' value='0' />";
                    var nobkup2 = "<input type='hidden' id='nobkup2" + index + "' value='0' />";
                    var nobkup3 = "<input type='hidden' id='nobkup3" + index + "' value='0' />";
                    var nobkup4 = "<input type='hidden' id='nobkup4" + index + "' value='0' />";
                    var nobkup5 = "<input type='hidden' id='nobkup5" + index + "' value='0' />";
                    var nobkup6 = "<input type='hidden' id='nobkup6" + index + "' value='0' />";
                    /*
                     if (parseInt(aData['nilaiPajak']) > 0) {
                     pilihpajak = "<a id='inputpajak" + index + "' onclick='setketpajak(" + index + "," + aData['idBas'] + ")' class='fancybox fancybox.iframe' href='" + getbasepath() + "/bkuspjpajak/listpajak?target='_top'' title='Input Pajak SPJ'  ><i class='icon-edit'>";
                     } else {
                     pilihpajak = "<a id='inputpajak" + index + "' >";
                     }*/

                    var idbku = "<input type='hidden' id='idbku" + index + "' value='" + aData['idBku'] + "' />";


                    $('td:eq(0)', nRow).html(index + nobkup1 + nobkup2 + nobkup3 + nobkup4 + nobkup5 + nobkup6);
                    $('td:eq(2)', nRow).html(namaakun + idbku);
                    $('td:eq(3)', nRow).html(nilaiAngg);
                    $('td:eq(4)', nRow).html(nilaisp2d);
                    $('td:eq(5)', nRow).html(nilaisebelum);
                    $('td:eq(6)', nRow).html(nilaisisa);
                    $('td:eq(7)', nRow).html(nilaiinput);
                    $('td:eq(8)', nRow).html(pilihpajak);
                    $('td:eq(9)', nRow).html(nilaipajak + nilaip1 + nilaip2 + nilaip3 + nilaip4 + nilaip5 + nilaip6 + pajakunformat);
                    $('td:eq(10)', nRow).html(inputcek + idbas + textnamaakun + textkodeakun + penanda);
                    return nRow;
                },
                "aoColumns": [
                    {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "4%"},
                    {"mDataProp": "kodeakun", "bSortable": false, sClass: "left", "sWidth": "8%"},
                    {"mDataProp": "namaakun", "bSortable": false, sClass: "left", "sWidth": "15%"},
                    {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "right", "sWidth": "11%"},
                    {"mDataProp": "nilaiBkuSp2d", "bSortable": false, sClass: "right", "sWidth": "11%"},
                    {"mDataProp": "nilaiBkuSebelum", "bSortable": false, sClass: "right", "sWidth": "11%"},
                    {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "right", "sWidth": "11%"},
                    {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right", "sWidth": "11%"},
                    {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "4%"},
                    {"mDataProp": "idBas", "bSortable": false, sClass: "right", "sWidth": "11%"},
                    {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "3%"}
                ]
            });
        }
        else
        {
            myTable.fnClearTable(0);
            myTable.fnDraw();
        }
        getBanyakListSPJ();
        getNilaiSisaSpj();
        getNilaiValidasiSisaSpj();
        $("#sumspj").val(0);
        $("#sumpajak").val(0);
        $("#totalspjhidden").val(0);
         $("#totalpajakhidden").val(0);
    }

}

function setketpajak(index, idbas) {
    $("#idbastemp").val(idbas);
    $("#indextemp").val(index);
    
    window.localStorage.setItem("ketNilaiP1", $("#nilaip1" + index).val());
    window.localStorage.setItem("ketNilaiP2", $("#nilaip2" + index).val());
    window.localStorage.setItem("ketNilaiP3", $("#nilaip3" + index).val());
    window.localStorage.setItem("ketNilaiP4", $("#nilaip4" + index).val());
    window.localStorage.setItem("ketNilaiP5", $("#nilaip5" + index).val());
    window.localStorage.setItem("ketNilaiP6", $("#nilaip6" + index).val());
    //accounting.unformat($("#sumpajak").val(), ",")
    window.localStorage.setItem("ketSumPajak", accounting.unformat($("#totalpajak" + index).val(), ","));
}

function enablerow(obj, index) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
        $("#penanda" + index).val(1);
    } else {
        $("#penanda" + index).val(0);
    }
    setdisabled(param, index);
    hitungnilaispj();
    setpajak(index, param);
}

function setpajak(index, param) {
    var text;
    if (param == "readonly") {
        text = "<a id='inputpajak" + index + "' >";
        $("#nilaip1" + index).val(0);
        $("#nilaip2" + index).val(0);
        $("#nilaip3" + index).val(0);
        $("#nilaip4" + index).val(0);
        $("#nilaip5" + index).val(0);
        $("#nilaip6" + index).val(0);
        $("#totalpajak" + index).val(0);
        $("#pajakunformat" + index).val(0);
    } else {
        text = "<a id='inputpajak" + index + "' onclick='setketpajak(" + index + "," + $("#idbas" + index).val() + ")' class='fancybox fancybox.iframe' href='" + getbasepath() + "/bkuspjpajak/listpajak?target='_top'' title='Input Pajak SPJ'  ><i class='icon-edit'>";
    }
    $("#inputpajak" + index).html(text);
    hitungtoralpajak();
}

function hitungtoralpajak() {

    var total = 0;
    for (var a = 1; a <= jumbarisspj; a++) {
        total += parseFloat(accounting.unformat($("#totalpajak" + a).val(), ","));
        //total += $("#pajakunformat" + a).val();
    }

    $("#sumpajak").val(accounting.formatNumber(total, 2, '.', ","));
    $("#totalpajakhidden").val(total);
}

function setnilaipajak() {

    for (var a = 1; a <= jumbarisspj; a++) {
        var nilai = $("#pajakunformat" + a).val();
        $("#totalpajak" + a).val(accounting.formatNumber(nilai, 2, '.', ","));
    }

}


function setdisabled(param, index) {
    $("#nilaiinput" + index).attr("readonly", param);
}

function validasipajak(index) {
    var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var nilaipajak = $("#pajakunformat" + index).val();
    //var total = 0;

    if (nilaiinput < nilaipajak) {
        bootbox.alert("Nilai SPJ tidak boleh lebih kecil dari Nilai Pajak.");
        //$('#nilaiinput' + index).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
        $('#nilaiinput' + index).autoNumeric('set', nilaipajak); // nilaisisa harus yang di unformat :)
    }

    hitungnilaispj();
}

function pasangvalidatebesarperfield(index) {
    var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var nilaisisa = accounting.unformat($("#nilaisisa" + index).val(), ",");
    //var total = 0;

    if (nilaiinput > nilaisisa) {
        bootbox.alert("Nilai SPJ tidak boleh lebih besar dari Sisa SPD."); // ganti pesan : sisa bku -> sisa spd
        //$('#nilaiinput' + index).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
        $('#nilaiinput' + index).autoNumeric('set', nilaisisa); // nilaisisa harus yang di unformat :)
    }

    hitungnilaispj();
}

function simpan() {

//$('#btnSimpan').attr("disabled", true);
    var filling = $('#fileInbox').val();
    var table3 = document.getElementById('spjtablebody');
    var rows3 = table3.rows;
    var jum3 = rows3.length;
    var tglPost = $("#tglPosting").val();
    var nobukti = $("#noBuktiDok").val();
    var tglDok = $("#tglDok").val();
    var datalengkap = true;
    var idKegiatanSpj = $("#idKegiatan").val();
    var bulanTglPost = tglPost.substr(3, 2);
    var tahunTglPost = tglPost.substr(6, 4);
    var bulan = $("#bulan").val();
    var nilaisisaspj = accounting.unformat($("#sisaspj").val(), ",");
    var nilaitotalspj = $("#totalspjhidden").val();
    var nilaisisaspd = accounting.unformat($("#sisaspd").val(), ",");
    var validasinilaispj, ketvalidasi;
    var nippptk = $("#nipPptk").val();
    var namapptk = $("#namaPptk").val();
    var datalengkap = true;
    var ketnilaipajak = true;
    var banyakcek = 0;
    var nilaitotalpajak = $("#totalpajakhidden").val();
    var nilaispjnetto = nilaitotalspj - nilaitotalpajak;
    
    if (nilaisisaspj <= nilaisisaspd) { // validasi ke nilai sisa spj
        validasinilaispj = nilaisisaspj;
        ketvalidasi = "Sisa Uang Persediaan (Kas SKPD)";
    } else { // validasi ke sisa spd
        validasinilaispj = nilaisisaspd;
        ketvalidasi = "Total Sisa SPD";
    }

    if ((jum3 > 0) && ($("#akunnama1").val() !== "")) {


        for (var a = 1; a <= jum3; a++) {
            if ($('#penanda' + a).val() == 1) {
                banyakcek = banyakcek + 1;
            }
        }

        if (banyakcek > 0) {
            for (var a = 1; a <= jum3; a++) {
                var nilai = parseFloat(accounting.unformat($('#nilaiinput' + a).val(), ","));
                var nilaipajak = parseFloat(accounting.unformat($('#totalpajak' + a).val(), ","));
                if ($('#penanda' + a).val() == 1 && nilai < 0) { // validasinya ganti, nilai boleh 0 
                    datalengkap = false;
                }

                if (nilai < nilaipajak) {
                    ketnilaipajak = false;
                }
            }
        } else if (banyakcek == 0) {
            datalengkap = false;
        }


        if (tglPost == "" || nobukti == "" || tglDok == "" || idKegiatanSpj == "" || filling == "" || nippptk == "" || namapptk == "" || datalengkap == false) {
            bootbox.alert("Pengisian Data Harus Lengkap");
        } else if (bulanTglPost !== bulan) {
            bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
        } else if (tahunTglPost !== $("#tahun").val()) {
            bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
        } else if (ketnilaipajak == false) {
            bootbox.alert("Nilai SPJ Tidak Boleh Lebih Kecil dari Nilai Pajak ");
        } else if (nilaitotalspj > validasinilaispj) {
            bootbox.alert("Total SPJ Tidak Boleh Lebih Besar dari " + ketvalidasi);
        } else if ((nilaitotalspj > 50000000) && ($("#kodePembayaran").val() == "1")) { // Tunai

            bootbox.confirm("Apakah Transaksi Senilai Rp " + $("#sumspj").val() + " Akan Dilakukan Secara Tunai ? ", function(result) {
                if (result) {

                    updateSPJ();
                } else {
                    bootbox.hideAll();
                    return result;
                }
            });
        } else {
            updateSPJ();
        }

    }

}

function updateSPJ() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#inboxFile").val();
    var dd, mm, yy, tanggal;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;
    var status;
    if (jumbarisspj > 0) {

        var varurl = getbasepath() + "/bkuspjpajak/json/prosesupdatespj";
        var dataac = [];
        var nilailist = [];
        var nilaipajak = [];
        var i, j;
        var uraianbukti;
        for (i = 0; i < jumbarisspj; i++) { // list spj
            var id = i + 1;
            uraianbukti = "Dibayar " + $('#ketKegiatan').val() + " " + $("#namaakun" + id).val() + " - " + $("#uraian").val();
            var totalpajak = Math.round($("#pajakunformat" + id).val());
            var nilaibruto = parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ","));
            var nilainetto = nilaibruto - totalpajak;
            var pararr = {
                kodetransaksi: "JJ",
                nilaimasuk: "0",
                nilaikeluar: nilaibruto,
                idbas: $("#idbas" + id).val(),
                uraianbukti: uraianbukti.substr(0, 400),
                penanda: $("#penanda" + id).val(),
                kodeakun: $("#kodeakun" + id).val(),
                nilainetto: nilainetto,
                nilipajak: totalpajak,
                nobku: $("#noBKU").val(),
                status: "spj",
                idbku: $("#idbku" + id).val(),
            };
            //console.log("list pajak - i : " + i);
            nilailist[i] = pararr;
        }

        for (j = 0; j < jumbarisspj; j++) { // list pajak
            var id = j + 1;
            for (var k = 1; k <= 6; k++) {
                var row = k.toString() + id.toString();
                var nilaipajak = Math.round($('#nilaip' + row).val()); //parseFloat(accounting.unformat($('#nilaip' + row).val(), ","));
                var nobku = $('#nobkup' + row).val();
                var jenistrx = "P" + k;
                if (nobku > 0) {
                    status = "edit";
                } else {
                    status = "add";
                }
//console.log("Jenis Pajak"+jenistrx);
                if (nilaipajak > 0 || nobku > 0) { // untuk semua bku yang telah input adalah edit, dan nilai > 0 yang baru adalah insert
                    var kettrx = "";

                    if (jenistrx == "P1") {
                        kettrx = "Pajak Penghasilan Pasal 21 atas kegiatan ";
                    } else if (jenistrx == "P2") {
                        kettrx = "Pajak Penghasilan Pasal 22 atas kegiatan ";
                    } else if (jenistrx == "P3") {
                        kettrx = "Pajak Penghasilan Pasal 23 atas kegiatan ";
                    } else if (jenistrx == "P4") {
                        kettrx = "Pajak Penghasilan Pasal 4 ayat (2) atas kegiatan ";
                    } else if (jenistrx == "P5") {
                        kettrx = "Pajak Pertambahan Nilai atas kegiatan ";
                    } else if (jenistrx == "P6") {
                        kettrx = "Pajak Penghasilan Pasal 26 atas kegiatan ";
                    }

                    uraianbukti = "Diterima " + kettrx + $('#ketKegiatan').val() + " " + $("#namaakun" + id).val();
                    var pararr = {
                        kodetransaksi: jenistrx,
                        nilaimasuk: nilaipajak,
                        nilaikeluar: "0",
                        idbas: $("#idbas" + id).val(),
                        uraianbukti: uraianbukti, //$("#uraian").val(),
                        penanda: "1",
                        kodeakun: $("#kodeakun" + id).val(),
                        nilainetto: "0",
                        nilipajak: "0",
                        nobku: nobku,
                        status: status,
                        idbku: "0",
                    };
                    //console.log("list pajak - i : " + i);
                    nilailist[i] = pararr;
                    i = i + 1;
                }
            }
        }

        var datajour = {
            tahun: tahun,
            idskpd: idskpd,
            tglposting: tanggal,
            nobukti: $("#noBukti").val(),
            tgldok: $("#tglDok").val(),
            jenis: "3",
            beban: $("#beban").val(),
            idkegiatan: $('#idKegiatan').val(),
            kodekegiatan: $('#kodeKeg').val(),
            fileinbox: fileinbox,
            uraianinput: $("#uraian").val(),
            carabayar: $("#kodePembayaran").val(),
            nipPPTK: $("#nipPptk").val(),
            namaPPTK: $("#namaPptk").val(),
            nobkuspj: $("#noBKU").val(),
            nilailist: nilailist

        }
        dataac = datajour;
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

            bootbox.alert("Data Buku Kas Umum Berhasil Diubah");
            gridspj();
            getBanyakListSPJ();
        });
    } else {

    }

}

function clearrowspj() {
    var i;
    var table = document.getElementById('spjtablebody');
    var rows = table.rows;
    var jum = rows.length;
    idrow = 0;
    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-0 sampai jumlahnya habis
    }
}

function getKegiatan() {
    var beban = $('#bebanSpjpop').val();
    var grid = document.getElementById('spjtable');
    var opt = "";
    if (beban == "UP/GU") {
        grid.rows[0].cells[4].innerText = 'Nilai LS (Kontrak)';
        grid.rows[0].cells[6].innerText = 'Sisa SPD';
        document.getElementById('textlabelsisaspj').innerHTML = 'Sisa Uang Persediaan UP/GU (Kas SKPD) :';
        document.getElementById('txtlabelnilaitu').innerHTML = 'Nilai SPP TU (4) :';
        beban = "UP";
        opt = '<option value="1" >1 - Tunai</option>';
        opt += '<option value="2" selected >2 - Bank/Transfer/Cek</option>';
    } else {
        grid.rows[0].cells[4].innerText = 'Nilai SPP TU';
        grid.rows[0].cells[6].innerText = 'Sisa SPP TU';
        document.getElementById('textlabelsisaspj').innerHTML = 'Sisa Uang Persediaan TU (Kas SKPD) :';
        document.getElementById('txtlabelnilaitu').innerHTML = 'Nilai BKU TU (4) :';
        opt = '<option value="2" selected >2 - Bank/Transfer/Cek</option>';
    }
    
    $("#kodePembayaran").html(opt);
//console.log("beban SPJ = " + beban);
    $('#beban').prop("disabled", true);
    $('#idKegiatanSpj').val($('#idKegpop').val());
    $("#keteranganKegPop").val($('#ketKegpop').val());
    $("#kodeKegSpj").val($('#kodeKegpop').val());
    $("#beban").val(beban);
    gridspj();
}

function setCurrentMonth() {
    var bulan = parseInt($("#bulan").val());
    var currentTime = new Date();
    var startDateFrom = new Date(currentTime.getFullYear(), bulan - 1, 1);
    var startDateTo = new Date(currentTime.getFullYear(), bulan, 0);
    $("#tglPosting").datepicker("refresh");
    $("#tglPosting").datepicker({
//dateFormat: 'dd.mm.yy',
//showClear: true,     
//changeMonth: true,
        clearDates: true,
        startDate: startDateFrom,
        endDate: startDateTo,
        autoclose: true,
        // inline: true,
        onSelect: function() {
            $('#tglPosting').datepicker('option', 'startDate', startDateFrom);
            $('#tglPosting').datepicker('option', 'endDate', startDateTo);
            $('#tglPosting').datepicker('show');
        }
    });
}

function hitungnilaispj() {
    var total = 0;
    var searchIDs = $("#spjtable input:checkbox:checked").map(function() {
        return $(this).val();
    }).get();
    //console.log("searchIDs = "+ searchIDs);
    $.each(searchIDs, function(idx, data) {
        total += parseFloat(accounting.unformat($("#nilaiinput" + data).val(), ","));
    })
    $("#sumspj").val(accounting.formatNumber(total, 2, '.', ","));
    $("#totalspjhidden").val(total);
}

function getNilaiSisaSpj() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var beban = $('#beban').val();
    var idkegiatan = $("#idKegiatan").val();
    var nobku = $("#noBKU").val();
    var carabayar = $("#kodePembayaran").val();
    $.getJSON(getbasepath() + "/bkuspjpajak/json/getNilaiSisaSpj", {tahun: tahun, idskpd: idskpd, nobku: nobku, beban: beban, idkegiatan: idkegiatan, carabayar: carabayar},
    function(result) {
        var banyak;
        var nilai = result.aData['nilaiSisa'];
        $('#sisaspj').val(accounting.formatNumber(nilai, 2, '.', ","));
        //console.log("nilai sisa spj = "+result.aData['nilaiSisa']);
    });
}

function getNilaiValidasiSisaSpj() { // dibuat 22 jan 16 by zainab, untuk memvalidasi total bku 

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkegiatan = $("#idKegiatan").val();
    var beban = $('#beban').val();
    var nobku = $("#noBKU").val();
    //console.log("idkegiatan = " + idkegiatan);
    if (idkegiatan !== "") {
        $.getJSON(getbasepath() + "/bkuspjpajak/json/getNilaiValidasiSisaSpj", {tahun: tahun, idskpd: idskpd, nobku: nobku, idkegiatan: idkegiatan, beban: beban},
        function(result) {

            var nilaispd = result.aData['nilaiSpd'];
            var nilaikontrak = result.aData['nilaiKontrak'];
            var nilaibku = result.aData['nilaiBku'];
            var nilaisisa = result.aData['nilaiSisa'];
            var nilaitu = result.aData['nilaiTU'];
            var setortu = result.aData['nilaiSetoranTU'];
            $('#nilaispd').val(accounting.formatNumber(nilaispd, 2, '.', ","));
            $('#nilaikontrak').val(accounting.formatNumber(nilaikontrak, 2, '.', ","));
            $('#nilaibku').val(accounting.formatNumber(nilaibku, 2, '.', ","));
            $('#sisaspd').val(accounting.formatNumber(nilaisisa, 2, '.', ","));
            $('#nilaispptu').val(accounting.formatNumber(nilaitu, 2, '.', ","));
            $('#nilaisetorantu').val(accounting.formatNumber(setortu, 2, '.', ","));
        });
    }

}

function getKodeTutupMax() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    $.getJSON(getbasepath() + "/bkuspjpajak/json/getKodeTutupMax", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan, tgltutup, nextkodebulan, nextbulan;
        var opt;
        banyakTutupMax = result.aData.length;
        if (banyak > 0) {
            kodebulan = result.aData[0]['kodeBulan'];
            bulan = result.aData[0]['bulan'];
            tgltutup = result.aData[0]['kodeTglTutup'];
            if (kodebulan == "01") {
                nextkodebulan = "02";
                nextbulan = "02 - Februari";
            } else if (kodebulan == "02") {
                nextkodebulan = "03";
                nextbulan = "03 - Maret";
            } else if (kodebulan == "03") {
                nextkodebulan = "04";
                nextbulan = "04 - April";
            } else if (kodebulan == "04") {
                nextkodebulan = "05";
                nextbulan = "05 - Mei";
            } else if (kodebulan == "05") {
                nextkodebulan = "06";
                nextbulan = "06 - Juni";
            } else if (kodebulan == "06") {
                nextkodebulan = "07";
                nextbulan = "07 - Juli";
            } else if (kodebulan == "07") {
                nextkodebulan = "08";
                nextbulan = "08 - Agustus";
            } else if (kodebulan == "08") {
                nextkodebulan = "09";
                nextbulan = "09 - September";
            } else if (kodebulan == "09") {
                nextkodebulan = "10";
                nextbulan = "10 - Oktober";
            } else if (kodebulan == "10") {
                nextkodebulan = "11";
                nextbulan = "11 - November";
            } else if (kodebulan == "11") {
                nextkodebulan = "12";
                nextbulan = "12 - Desember";
            } else if (kodebulan == "12") {
                nextkodebulan = "-";
                nextbulan = "Sudah Tutup Buku";
                $("#jenisTransaksi").attr('disabled', true);
                /* kalo uda akhir tahun dan sudah tutup semua (desember sudah ditutup), maka tidak bisa input lagi
                 * jadi pilihan jenis transaksi dikunci agar tidak bisa simpan
                 */
            }

            // menentukan bulan by tgl tutup
            if (tgltutup == "null" || tgltutup == null) {
                opt = '<option value="' + kodebulan + '" >' + kodebulan + " - " + bulan + '</option>';
                kodeTutupMax = kodebulan;
                bulanTutupMax = bulan;
            } else {
                opt = '<option value="' + nextkodebulan + '" >' + nextbulan + '</option>';
                kodeTutupMax = nextkodebulan;
                bulanTutupMax = nextbulan;
            }

        } else {
            opt = '<option value="01" >01 - Januari</option>';
        }

        $("#bulan").html(opt);
    });
}

function getKodeTutup() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    $.getJSON(getbasepath() + "/bkuspjpajak/json/getKodeTutup", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan, tgltutup, nextkodebulan, nextbulan;
        var opt;
        if (banyak > 0) {
            kodebulan = result.aData[0]['kodeBulan'];
            bulan = result.aData[0]['bulan'];
            tgltutup = result.aData[0]['kodeTglTutup'];
            if (kodebulan == "01") {
                nextkodebulan = "02";
                nextbulan = "02 - Februari";
            } else if (kodebulan == "02") {
                nextkodebulan = "03";
                nextbulan = "03 - Maret";
            } else if (kodebulan == "03") {
                nextkodebulan = "04";
                nextbulan = "04 - April";
            } else if (kodebulan == "04") {
                nextkodebulan = "05";
                nextbulan = "05 - Mei";
            } else if (kodebulan == "05") {
                nextkodebulan = "06";
                nextbulan = "06 - Juni";
            } else if (kodebulan == "06") {
                nextkodebulan = "07";
                nextbulan = "07 - Juli";
            } else if (kodebulan == "07") {
                nextkodebulan = "08";
                nextbulan = "08 - Agustus";
            } else if (kodebulan == "08") {
                nextkodebulan = "09";
                nextbulan = "09 - September";
            } else if (kodebulan == "09") {
                nextkodebulan = "10";
                nextbulan = "10 - Oktober";
            } else if (kodebulan == "10") {
                nextkodebulan = "11";
                nextbulan = "11 - November";
            } else if (kodebulan == "11") {
                nextkodebulan = "12";
                nextbulan = "12 - Desember";
            } else if (kodebulan == "12") {
                nextkodebulan = "-";
                nextbulan = "Sudah Tutup Buku";
                $("#jenisTransaksi").attr('disabled', true);
                /* kalo uda akhir tahun dan sudah tutup semua (desember sudah ditutup), maka tidak bisa input lagi
                 * jadi pilihan jenis transaksi dikunci agar tidak bisa simpan
                 */
            }

            // menentukan bulan by tgl tutup
            if (tgltutup == "null" || tgltutup == null) {
                opt = '<option value="' + kodebulan + '" >' + kodebulan + " - " + bulan + '</option>';
            } else {
                opt = '<option value="' + nextkodebulan + '" >' + nextbulan + '</option>';
            }

            $("#bulan").html(opt);
        } else { // jika awal tahun, belum ada yang insert data
            /*if (banyakTutupMax > 0) {
             opt = '<option value="' + kodeTutupMax + '" >' + bulanTutupMax + '</option>';
             } else {
             opt = '<option value="01" >01 - Januari</option>';
             }*/
            getKodeTutupMax();
        }

        // $("#bulan").html(opt);
    });
}

function getBanyakListSPJ() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkeg = $("#idKegiatan").val();
    var nobku = $('#noBKU').val();
    var beban = $("#beban").val();
    var nobukti = $('#noBukti').val();
    $.getJSON(getbasepath() + "/bkuspjpajak/json/getBanyakListSPJ", {tahun: tahun, idskpd: idskpd, idkegiatan: idkeg, nobku: nobku, beban: beban, nobukti: nobukti},
    function(result) {

        jumbarisspj = result;
        getDataPajak();
    });
}

function getBulanByRekap() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    $.getJSON(getbasepath() + "/bkuspjpajak/json/getBulanByRekap", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan;
        var opt;
        if (banyak > 0) {
            kodebulan = result.aData[0]['kodeBulan'];
            bulan = result.aData[0]['bulan'];
            if (kodebulan == "13") {
                kodebulan = "";
                bulan = "Sudah Tutup Buku Akhir Tahun";
                $("#jenisTransaksi").attr('disabled', true);
                /* kalo uda akhir tahun dan sudah tutup semua (desember sudah ditutup), maka tidak bisa input lagi
                 * jadi pilihan jenis transaksi dikunci agar tidak bisa simpan
                 */
            }

            opt = '<option value="' + kodebulan + '" >' + kodebulan + " - " + bulan + '</option>';
            $("#bulan").html(opt);
        } else { // jika awal tahun, belum ada yang insert data
            opt = '<option value="01" >01 - Januari</option>';
            $("#bulan").html(opt);
        }

    });
}

function tes() {
    for (var j = 0; j < jumbarisspj; j++) { // list pajak
        var id = j + 1;
        for (var k = 1; k <= 6; k++) {
            var row = k.toString() + id.toString();
            // console.log("ROW Pajak = "+row);

            var nilaipajak = parseFloat(accounting.unformat($('#nilaip' + row).val(), ","));
            var jenistrx = "P" + k;
            if (nilaipajak > 0) {
                console.log("Jenis Pajak = " + jenistrx);
                console.log("Nilai Pajak = " + nilaipajak);
                console.log("IDBAS Pajak = " + $("#idbas" + id).val());
                console.log("IDBAS Pajak = " + $("#kodeakun" + id).val());
            }
        }
    }
}

function getDataPajak() {
    var idskpd = $('#idskpd').val();
    var idkegiatan = $('#idKegiatan').val();
    var nobukti = $('#noBukti').val();
    var nobkuref = $("#noBKU").val();
    var tglPost = $("#tglPosting").val();
    
    var dd, mm, yy, tanggal;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;
    $.getJSON(getbasepath() + "/bkuspjpajak/json/getDataPajak", {idskpd: idskpd, idkegiatan: idkegiatan, nobukti: nobukti, tglpost: tanggal, nobkuref:nobkuref},
    function(result) {
        var banyak, kode, ket, id, idbasbaris, idbasresult;
        banyak = result.aData.length;
        //console.log("jum baris spj == " + jumbarisspj);
        if (banyak > 0) {

            for (var j = 0; j < banyak; j++) {
                for (var i = 1; i <= jumbarisspj; i++) {
                    id = result.aData[j]['kodeTransaksi'] + (i).toString();
                    idbasbaris = $("#idbas" + i).val();
                    idbasresult = result.aData[j]['idBas'];
                    if (idbasbaris == idbasresult) {
                        //console.log("id pajak = " + id);
                        $("#nilai" + id).val(result.aData[j]['nilaiPajak']);
                        $("#nobku" + id).val(result.aData[j]['noBKU']);
                    }
                }
            }
        }
    });
}