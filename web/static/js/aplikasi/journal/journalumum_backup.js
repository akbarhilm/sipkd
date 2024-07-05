
$(document).ready(function() {
    setreadonly();
    setbeban();
});

var idbuton;


function setenable(id) {
    var baris = id.substring(8);

    if (document.getElementById("cekpilih" + baris).checked == true) {
        document.getElementById("ketskpd" + baris).readOnly = false;
        document.getElementById("kodeakun" + baris).readOnly = false;
        document.getElementById("debet" + baris).readOnly = false;
        document.getElementById("kredit" + baris).readOnly = false;
        //document.getElementById("kegiatan" + baris).readOnly = false;

        $('#jenis' + baris).attr("disabled", false);
        $('#beban' + baris).attr("disabled", false);

        document.getElementById("pilih" + baris).style.visibility = "visible";
        document.getElementById("akunpilih" + baris).style.visibility = "visible";
        
        $('#status' + baris).val(0);

    } else {
        document.getElementById("ketskpd" + baris).readOnly = true;
        document.getElementById("kodeakun" + baris).readOnly = true;
        document.getElementById("debet" + baris).readOnly = true;
        document.getElementById("kredit" + baris).readOnly = true;
        document.getElementById("kegiatan" + baris).readOnly = true;

        $('#jenis' + baris).attr("disabled", true);
        $('#beban' + baris).attr("disabled", true);

        document.getElementById("pilih" + baris).style.visibility = "hidden";
        document.getElementById("akunpilih" + baris).style.visibility = "hidden";
        // SET VALUE ""
        $('#ketskpd' + baris).val("");
        $('#kodeakun' + baris).val("");
        $('#namaakun' + baris).val("");
        $('#kegiatan' + baris).val("");
        $('#debet' + baris).val(0);
        $('#kredit' + baris).val(0);

        $('#status' + baris).val(1);
        getTotal();
    }

}
function setreadonly() {
    var i;

    for (i = 1; i <= 10; i++) {

        // for textbox
        document.getElementById("ketskpd" + i).readOnly = true;
        document.getElementById("kodeakun" + i).readOnly = true;
        document.getElementById("debet" + i).readOnly = true;
        document.getElementById("kredit" + i).readOnly = true;
        document.getElementById("kegiatan" + i).readOnly = true;

        // for combobox
        $('#jenis' + i).attr("disabled", true);
        $('#beban' + i).attr("disabled", true);
        
        // for fancybox button
        document.getElementById("pilih" + i).style.visibility = "hidden";
        document.getElementById("akunpilih" + i).style.visibility = "hidden";

    }
}

function cektotal() {
    submitnilai();
}

function submitnilai() {

    var tkredit = $('#totalkredit').val();
    var tdebet = $('#totaldebet').val();
    var tglpost = $('#tglPosting').val();

    if (tkredit == tdebet) {
        if (tglpost == ""){
            bootbox.alert("Isi Tanggal Journal Terlebih Dulu");
        }else {
            var varurl = getbasepath() + "/journal/json/prosessimpan";
            var dataac = [];
            var datajour = {
                idskpd1: $("#idskpd1").val(),
                idskpd2: $("#idskpd2").val(),
                idskpd3: $("#idskpd3").val(),
                idskpd4: $("#idskpd4").val(),
                idskpd5: $("#idskpd5").val(),
                idskpd6: $("#idskpd6").val(),
                idskpd7: $("#idskpd7").val(),
                idskpd8: $("#idskpd8").val(),
                idskpd9: $("#idskpd9").val(),
                idskpd10: $("#idskpd10").val(),
                idbas1: $("#idbas1").val(),
                idbas2: $("#idbas2").val(),
                idbas3: $("#idbas3").val(),
                idbas4: $("#idbas4").val(),
                idbas5: $("#idbas5").val(),
                idbas6: $("#idbas6").val(),
                idbas7: $("#idbas7").val(),
                idbas8: $("#idbas8").val(),
                idbas9: $("#idbas9").val(),
                idbas10: $("#idbas10").val(),
                debet1: $("#debet1").val(),
                debet2: $("#debet2").val(),
                debet3: $("#debet3").val(),
                debet4: $("#debet4").val(),
                debet5: $("#debet5").val(),
                debet6: $("#debet6").val(),
                debet7: $("#debet7").val(),
                debet8: $("#debet8").val(),
                debet9: $("#debet9").val(),
                debet10: $("#debet10").val(),
                kredit1: $("#kredit1").val(),
                kredit2: $("#kredit2").val(),
                kredit3: $("#kredit3").val(),
                kredit4: $("#kredit4").val(),
                kredit5: $("#kredit5").val(),
                kredit6: $("#kredit6").val(),
                kredit7: $("#kredit7").val(),
                kredit8: $("#kredit8").val(),
                kredit9: $("#kredit9").val(),
                kredit10: $("#kredit10").val(),
                beban1: $("#beban1").val(),
                beban2: $("#beban2").val(),
                beban3: $("#beban3").val(),
                beban4: $("#beban4").val(),
                beban5: $("#beban5").val(),
                beban6: $("#beban6").val(),
                beban7: $("#beban7").val(),
                beban8: $("#beban8").val(),
                beban9: $("#beban9").val(),
                beban10: $("#beban10").val(),
                kegiatan1: $("#kegiatan1").val(),
                kegiatan2: $("#kegiatan2").val(),
                kegiatan3: $("#kegiatan3").val(),
                kegiatan4: $("#kegiatan4").val(),
                kegiatan5: $("#kegiatan5").val(),
                kegiatan6: $("#kegiatan6").val(),
                kegiatan7: $("#kegiatan7").val(),
                kegiatan8: $("#kegiatan8").val(),
                kegiatan9: $("#kegiatan9").val(),
                kegiatan10: $("#kegiatan10").val(),
                status1: $("#status1").val(),
                status2: $("#status2").val(),
                status3: $("#status3").val(),
                status4: $("#status4").val(),
                status5: $("#status5").val(),
                status6: $("#status6").val(),
                status7: $("#status7").val(),
                status8: $("#status8").val(),
                status9: $("#status9").val(),
                status10: $("#status10").val(),
                jenis1: $("#jenis1").val(),
                jenis2: $("#jenis2").val(),
                jenis3: $("#jenis3").val(),
                jenis4: $("#jenis4").val(),
                jenis5: $("#jenis5").val(),
                jenis6: $("#jenis6").val(),
                jenis7: $("#jenis7").val(),
                jenis8: $("#jenis8").val(),
                jenis9: $("#jenis9").val(),
                jenis10: $("#jenis10").val(),
                tglPosting: $("#tglPosting").val(),
                noDok: $("#noDok").val(),
                tglDok: $("#tglDok").val(),
                noJournal: $("#noJournal").val(),
                e_jour: $("#e_jour").val()
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
                //grid();
                bootbox.alert("Data Journal Berhasil Disimpan");
            });
        }
        
    } else {
        bootbox.alert("Data Journal Gagal Disimpan (Total Debet Tidak Sama Dengan Total Kredit)");
    }

}

function validasiDebet(id) {
    var i = id.substring(5);
    if ($('#debet'+i).val() != 0) {
        parseFloat(accounting.unformat($('#kredit' + i).val(0), ","));
        getTotal();
    }
}

function validasiKredit(id) {
    var i = id.substring(6);
    if ($('#kredit'+i).val() != 0) {
        parseFloat(accounting.unformat($('#debet' + i).val(0), ","));
        getTotal();
    }
}

function getTotal() {
    var i, totdeb, totkre, nilaid, nilaik;
    totdeb = 0;
    totkre = 0;

    for (i = 1; i <= 10; i++) {
        if ($('#debet' + i).val() == "") {
            nilaid = 0;
        } else {
            nilaid = parseFloat(accounting.unformat($('#debet' + i).val(), ","));
        }

        if ($('#kredit' + i).val() == "") {
            nilaik = 0;
        } else {
            nilaik = parseFloat(accounting.unformat($('#kredit' + i).val(), ","));
        }

        totdeb = totdeb + nilaid;
        totkre = totkre + nilaik;
    }

    $('#totaldebet').val(accounting.formatNumber(totdeb));
    $('#totalkredit').val(accounting.formatNumber(totkre));

}

function cariakun(kode, evt, nama) {
    var e = evt || window.event;
    var key = e.keyCode || e.which;

    if (!e.shiftKey && !e.altKey && !e.ctrlKey &&
            key == 13) {
        getnama(kode, nama);
    }
}

function getnama(kode, nama) {
    $.getJSON(getbasepath() + "/journal/json/getnamaakun", {kode: kode},
    function(result) {
        var id = nama.substring(8);

        if (result.aData.length > 0) {
            var namaakun = result.aData[0]['namaakun'];
            var idbas = result.aData[0]['idBas'];

            $('#namaakun' + id).val(namaakun);
            $('#idbas' + id).val(idbas);

            //bootbox.alert("KODE AKUN = " + kode + " -- NAMA AKUN = " + namaakun + " -- IDBAS = " + idbas);

        } else {
            bootbox.alert("Kode Akun Tidak Ada!");
            $('#namaakun' + id).val("");
        }

    });
}

function getbutton(id) {
    idbuton = id.substring(5);
}

function getidskpd() {
    //bootbox.alert("CEK NAMA SKPD1 POP UP = "+$('#skpdpop').val());
    var id = $('#idskpdpop').val();
    var keterangan = $('#skpdpop').val();

    $('#idskpd' + idbuton).val(id);
    $('#ketskpd' + idbuton).val(keterangan);

}

function getidakun() {
   
    var id = idbuton.substring(4);
    
    $('#idbas' + id).val($('#idbaspop').val());
    $('#kodeakun' + id).val($('#idakunpop').val());
    $('#namaakun' + id).val($('#namaakunpop').val());
}


function setkriteria(kodejenis,jenis) { 
    var id =jenis.substring(5);
    var opttemuan = '<option value="LS">Langsung</option>';
    
    document.getElementById("kegiatan" + id).readOnly = true;
    
    if (kodejenis == '3') {
        document.getElementById("kegiatan" + id).readOnly = false;
        opttemuan += '<option value="UP">UP/GU</option>';
        opttemuan += '<option value="TU">TU</option>';
    }
    
    if (kodejenis == '5' || kodejenis == '9') {
        var opttemuan = '<option value="">-</option>';
    }

    $("#beban"+id).html(opttemuan);
   
}

function setbeban(){
    var kode =  $("#jenis1").val();
    var i,id;
    
    for (i = 1; i <= 10; i++) {
        id = "jenis"+i.toString();
        setkriteria(kode,id);
    }
    
}


