$(document).ready(function() {

    document.getElementById("labelnippptk").style.display = "block";
    document.getElementById("labelnamapptk").style.display = "block";

    setPanel();
    getBulanBySaldoAwal();//getKodeTutup();
    //getbanyakrinci();
});

// global variable
var idrow = 0;
var banyakdata = 0;
var idbuton;
var jumbarisnp = 0;
var jumbarisnm = 0;
var banyakTutupMax, kodeTutupMax, bulanTutupMax;

function setPanel() {
    var jenistrans = $("#kodeTransaksi").val();
    var a = document.getElementById("pilihKeg");

    if (jenistrans == "NP") {
        $('#tabelNM').hide();
        $('#tabelNP').show();
        a.href = getbasepath() + "/bku/listkegiatannp?target='_top'";
        gridnp();

    } else if (jenistrans == "NM") {
        $('#tabelNM').show();
        $('#tabelNP').hide();
        a.href = getbasepath() + "/bku/listkegiatannm?target='_top'";
        gridnm();
    }
}

function cekLengkap() {
    var tglPost = $("#tglPosting").val();
    var nobukti = $("#noBukti").val();
    var tglDok = $("#tglDok").val();
    var datalengkap = true;
    var jenistransaksi = $("#kodeTransaksi").val();
    var filling = $("#inboxFile").val();

    var table4 = document.getElementById('nptablebody');
    var rows4 = table4.rows;
    var jumnp = rows4.length;

    var table5 = document.getElementById('nmtablebody');
    var rows5 = table5.rows;
    var jumnm = rows5.length;

    var bulanTglPost = tglPost.substr(3, 2);
    var bulan = $("#bulan").val();
    var uraianpn = $('#uraianPn').val();
    var uraianpg = $('#uraianPg').val();

    if (jenistransaksi == "NM") { // NM
        datalengkap = true;
        var banyakcek = 0;

        if ((jumnm > 0) && ($("#nilaisebelum1").val() !== "")) {

            for (var a = 1; a <= jumnm; a++) {
                if ($('#penanda' + a).val() == 1) {
                    banyakcek = banyakcek + 1;
                }
            }

            if (banyakcek > 0) {
                for (var a = 1; a <= jumnm; a++) {
                    var nilai = parseFloat(accounting.unformat($('#nilaiinput' + a).val(), ","));

                    if ($('#penanda' + a).val() == 1 && nilai < 0) {
                        datalengkap = false;
                    }
                }
            } else if (banyakcek == 0) {
                datalengkap = false;
            }


            if (tglPost == "" || nobukti == "" || tglDok == "" || $("#idKegiatan").val() == "" || filling == "" || uraianpn == "" || uraianpg == "" || datalengkap == false) {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else if (bulanTglPost !== bulan) {
                bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
            } else {
                update();
            }
        }
    }

    if (jenistransaksi == "NP") {
        datalengkap = true;
        var banyakcek = 0;

        if ((jumnp > 0) && ($("#nilaisebelum1").val() !== "")) {

            for (var a = 1; a <= jumnp; a++) {
                if ($('#penanda' + a).val() == 1) {
                    banyakcek = banyakcek + 1;
                }
            }

            if (banyakcek > 0) {
                for (var a = 1; a <= jumnp; a++) {
                    var nilai = parseFloat(accounting.unformat($('#nilaiinput' + a).val(), ","));

                    if ($('#penanda' + a).val() == 1 && nilai < 0) {
                        datalengkap = false;
                    }
                }
            } else if (banyakcek == 0) {
                datalengkap = false;
            }

            if (tglPost == "" || nobukti == "" || tglDok == "" || $("#idKegiatan").val() == "" || filling == "" || datalengkap == false) {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else if (bulanTglPost !== bulan) {
                bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
            } else {
                update();
            }
        }
    }

}

function update() {

    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal, jumbaris;
    var akunpn, akunpg;
    var jenistrans = $('#kodeTransaksi').val();
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;
    var fileinbox = $("#inboxFile").val();
    var uraianpn = $('#uraianPn').val();
    var uraianpg = $('#uraianPg').val();
    var carabayar = $('#kodePembayaran').val();
    var akunbayar, idbayar;
    var idbaspn, idbaspg, carabayarpn, carabayarpg;
    
    if (carabayar == 1) {
        akunbayar = "1.1.1.03.01.001"; // tunai
        idbayar = "9947";

    } else if (carabayar == 2) {
        akunbayar = "1.1.1.03.01.002"; // bank
        idbayar = "9948";
    }
    
    if (jenistrans == "NP") {
        akunpn = "1.1.1.03.01.003"; // panjar
        akunpg = akunbayar; //"1.1.1.03.01.001"; // tunai
        jumbaris = jumbarisnp;
        idbaspn = "9949";
        idbaspg = idbayar; //"9947";
        carabayarpn = "3";
        carabayarpg = carabayar;

    } else if (jenistrans == "NM") {
        akunpn = akunbayar; // "1.1.1.03.01.001"; // tunai
        akunpg = "1.1.1.03.01.003"; // panjar
        jumbaris = jumbarisnm;
        idbaspn = idbayar; //"9947";
        idbaspg = "9949";
        carabayarpn = carabayar; //"1";
        carabayarpg = "3";
    }

    var varurl = getbasepath() + "/bku/json/simpanupdatenpd";
    var dataac = [];
    var nilailist = [];
    var i;

    for (i = 0; i < jumbaris; i++) { // list
        var id = i + 1;

        var pararr = {
            nilainpd: parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ",")),
            penanda: $("#penanda" + id).val()
        };
        nilailist[i] = pararr;
    }

    console.log();

    var datajour = {
        tahun: tahun,
        idskpd: idskpd,
        tglposting: tanggal,
        kodetransaksi: $('#kodeTransaksi').val(),
        nobukti: $("#noBukti").val(),
        tgldok: $("#tglDok").val(),
        jenis: "3",
        beban: $("#beban").val(),
        idkegiatan: $("#idKegiatan").val(),
        kodekegiatan: $("#kodeKeg").val(),
        fileinbox: fileinbox,
        akunpn: akunpn,
        akunpg: akunpg,
        namapptk: $("#namaPptk").val(),
        nippptk: $("#nipPptk").val(),
        nobku: $('#noBKU').val(),
        uraianpn: uraianpn,
        uraianpg: uraianpg,
        carabayarpn: carabayarpn,
        carabayarpg: carabayarpg,
        idbaspn: idbaspn,
        idbaspg: idbaspg,
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

        clearrow();
        if (jenistrans == "NP") {
            gridnp();
        } else if (jenistrans == "NM") {
            gridnm();
        }

        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
    });

}

function clearrow() {
    var i;
    var jenistrans = $("#kodeTransaksi").val();
    var table; //= document.getElementById('nptablebody');

    if (jenistrans == "NP") {
        table = document.getElementById('nptablebody');
    } else if (jenistrans == "NM") {
        table = document.getElementById('nmtablebody');
    }

    var rows = table.rows;
    var jum = rows.length;

    idrow = 0;

    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-0 sampai jumlahnya habis
    }
}

function gridnp() {
    jumbarisnp = 0;

    if (typeof myTablenp == 'undefined') {
        myTablenp = $('#nptable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku/json/listbkunp",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idskpd", value: $("#idskpd").val()},
                {name: "idkegiatan", value: $("#idKegiatan").val()},
                {name: "nobku", value: $("#noBKU").val()}
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

                var penanda = "<input type='hidden' id='penanda" + index + "' value='" + 1 + "' />";
                var nilainpd = "<input type='text' name='nilainpd" + index + "' id='nilainpd" + index + "'  class='inputmoney'  value='" + aData['nilaiNpd'] + "' readOnly='true' />";
                var nilaisebelum = "<input type='text' name='nilaisebelum" + index + "' id='nilaisebelum" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSebelum'] + "' readOnly='true' />";
                var nilaisisa = "<input type='text' name='nilaisisa" + index + "' id='nilaisisa" + index + "'  class='inputmoney'  value='" + aData['nilaiSisa'] + "' readOnly='true' />";
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' onkeyup='pasangvalidatebesarperfield(" + index + ")' />";
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") checked />";
                var nilaianggaran = "<input type='text' name='nilaiangg" + index + "' id='nilaiangg" + index + "'  class='inputmoney'  value='" + aData['nilaiAnggaran'] + "' readOnly='true' />";
                var nilaisp2d = "<input type='text' name='nilaisp2d" + index + "' id='nilaisp2d" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSp2d'] + "' readOnly='true' />";
                var nilaispj = "<input type='text' name='nilaispj" + index + "' id='nilaispj" + index + "'  class='inputmoney'  value='" + aData['nilaiSpj'] + "' readOnly='true' />";
                jumbarisnp = jumbarisnp + 1;

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(nilaianggaran);
                $('td:eq(2)', nRow).html(nilaisp2d);
                $('td:eq(3)', nRow).html(nilaisebelum);
                $('td:eq(4)', nRow).html(nilainpd);
                $('td:eq(5)', nRow).html(nilaispj);
                $('td:eq(6)', nRow).html(nilaisisa);
                $('td:eq(7)', nRow).html(nilaiinput);
                $('td:eq(8)', nRow).html(inputcek + penanda);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuSp2d", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuSebelum", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiNpd", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiSpj", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTablenp.fnClearTable(0);
        myTablenp.fnDraw();
    }
}

function gridnm() {
    jumbarisnm = 0;

    if (typeof myTablenm == 'undefined') {
        myTablenm = $('#nmtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku/json/listbkunm",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idskpd", value: $("#idskpd").val()},
                {name: "idkegiatan", value: $("#idKegiatan").val()},
                {name: "nobku", value: $("#noBKU").val()}
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

                var penanda = "<input type='hidden' id='penanda" + index + "' value='" + 1 + "' />";
                var nilainpd = "<input type='text' name='nilainpd" + index + "' id='nilainpd" + index + "'  class='inputmoney'  value='" + aData['nilaiNpd'] + "' readOnly='true' />";
                var nilaisebelum = "<input type='text' name='nilaisebelum" + index + "' id='nilaisebelum" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSebelum'] + "' readOnly='true' />";
                var nilaisisa = "<input type='text' name='nilaisisa" + index + "' id='nilaisisa" + index + "'  class='inputmoney'  value='" + aData['nilaiSisa'] + "' readOnly='true' />";
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' onkeyup='pasangvalidatebesarperfield(" + index + ")' />";
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") checked />";
                jumbarisnm = jumbarisnm + 1;

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(nilainpd);
                $('td:eq(2)', nRow).html(nilaisebelum);
                $('td:eq(3)', nRow).html(nilaisisa);
                $('td:eq(4)', nRow).html(nilaiinput);
                $('td:eq(5)', nRow).html(inputcek + penanda);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "nilaiNpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiNpd", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuSebelum", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiNpd", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTablenm.fnClearTable(0);
        myTablenm.fnDraw();
    }
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
}
function setdisabled(param, index) {
    $("#nilaiinput" + index).attr("readonly", param);

}

function pasangvalidatebesarperfield(index) {
    var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var nilaisisa = accounting.unformat($("#nilaisisa" + index).val(), ",");

    if (nilaiinput > nilaisisa) {
        bootbox.alert("Nilai BKU tidak boleh lebih besar dari Sisa BKU.");
        $('#nilaiinput' + index).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
    }
}

function getKodeTutupMax() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    console.log("masuk getKodeTutupMax");
    $.getJSON(getbasepath() + "/bku/json/getKodeTutupMax", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan, tgltutup, nextkodebulan, nextbulan;
        var opt;

        banyakTutupMax = result.aData.length;
        console.log("masuk banyakTutupMax = "+banyakTutupMax);
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

    $.getJSON(getbasepath() + "/bku/json/getKodeTutup", {tahun: tahun, idskpd: idskpd},
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

function getBulanBySaldoAwal() {
    //getSuadana();
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/bku/json/getSaldoAwal", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var saldo, opt;

        if (banyak > 0) {
            saldo = result.aData[0]['saldoKas'];
            saldoAwal = result.aData[0]['saldoKas'];

            if (saldo > 0) {
                opt = '<option value="01" >01 - Januari</option>';
                $("#bulan").html(opt);

            } else {
                getBulanByRekap();
                //getKodeTutup();
            }
        }
    });
}

function getBulanByRekap() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/bku/json/getBulanByRekap", {tahun: tahun, idskpd: idskpd},
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
