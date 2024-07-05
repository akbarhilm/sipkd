$(document).ready(function() {
    setawal();
});


function setawal(){
    $('#btnproses').attr("disabled", true);
    $('#btndraft').attr("disabled", true);
    $('#btncetak').attr("disabled", true);
    $('#btncetakxls').attr("disabled", true);
    $('#jenislaporan').attr("disabled", true);
    document.getElementById("labelkegiatan").style.display = "none"; //none
}

function setskpd(){
    $('#jenislaporan').attr("disabled", false);
    var jenis = $('#jenislaporan').val();
    setpanel(jenis);
    
}

function setpanel(jenis){
    
     if (jenis == 1){ // BKU Pengeluaran
         $('#btnproses').attr("disabled", false);
         document.getElementById("labelkegiatan").style.display = "none";
         $('#idKegiatan').val(0);
         $('#namaKeg').val("");
         setComboBulan2();
         
     } else if (jenis == 2){ // BKU Per Kegiatan
         $('#btnproses').attr("disabled", true);
         document.getElementById("labelkegiatan").style.display = "block";
         setComboBulan();
     } 
    setTanggalAkhir();
    //$('#btndraft').attr("disabled", false);
    //$('#btncetak').attr("disabled", false);
}

function setTanggalAkhir(){
    var tgl = $("#bulan").find(":selected").text();
    var index = tgl.indexOf("BELUM DIPROSES");
    var index2 = tgl.indexOf("DRAFT DIPROSES");
    //var belum = tgl.substring(5);
    var dd, mm, yy, tanggal;

    dd = tgl.substr(5, 2);
    mm = tgl.substr(8, 2);
    yy = tgl.substr(11, 4);

    tanggal = yy + mm + dd;

    $('#tgl2').val(tanggal);

    if (index != -1) { //jika belum diproses
        ///bootbox.alert("belum proses = "+tgl+belum);
        //$("#bulan").val(15); // tidak ada data
        $('#btndraft').attr("disabled", false);
        $('#btnproses').attr("disabled", false);
        $('#btncetak').attr("disabled", true);
        $('#btncetakxls').attr("disabled", true);

    } else if(index2 != -1){
        $('#btndraft').attr("disabled", false);
        $('#btnproses').attr("disabled", false);
        $('#btncetak').attr("disabled", false);
        $('#btncetakxls').attr("disabled", false);
    }
    else {
        //bootbox.alert("sudah proses = "+index+" = "+tgl+belum);
        $('#btndraft').attr("disabled", true);
        $('#btnproses').attr("disabled", true);
        $('#btncetak').attr("disabled", false);
        $('#btncetakxls').attr("disabled", false);
    }
    
    gridbkupengeluaran();
}

function setComboBulan() {
    var idskpd = $('#idskpdpop').val();
    var tahun = $('#tahun').val();
    
    $.getJSON(getbasepath() + "/laporanbkukeluar/json/setComboBulan", { idskpd: idskpd, tahun : tahun},
    function(result) {
        var banyak, kode,ket;
        var opt = "";
        
        banyak = result.aData.length;
        
        if (banyak > 0){
            
             for (var i = 0; i < banyak; i++) {
                 kode = result.aData[i]['kodeBulan'];
                 ket = result.aData[i]['bulanPosting'];
                
                 opt += '<option value="'+ kode + '" >' + ket + '</option>';
            }
        }
       
        $("#bulan").html(opt);
        
    });
    
}

function setComboBulan2() {
    var idskpd = $('#idskpdpop').val();
    var tahun = $('#tahun').val();
    
    $.getJSON(getbasepath() + "/laporanbkukeluar/json/setComboBulan2", { idskpd: idskpd, tahun : tahun},
    function(result) {
        var banyak, kode,ket;
        var opt = "";
        
        banyak = result.aData.length;
        
        if (banyak > 0){
            
             for (var i = 0; i < banyak; i++) {
                 kode = result.aData[i]['kodeBulan'];
                 ket = result.aData[i]['bulanPosting'];
                
                 opt += '<option value="'+ kode + '" >' + ket + '</option>';
            }
        }
       
        $("#bulan").html(opt);
        
    });
    
}

function cetakjurnalxls() {
    window.location.href = getbasepath() + "/laporanbkukeluar/xls/bkuxls?tahun=" + $("#tahun").val()
            + "&idskpd=" + $('#idskpdpop').val() + "&bulan=" 
            + $('#bulan').val();
}

function simpan() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
    var bulan = $("#bulan").val();
    
    console.log("masuk simpan");
    
    var urljson = getbasepath() + "/laporanbkukeluar/json/prosessimpan";

      //  var varurl = getbasepath() + "/bukubesarskpd/json/prosessimpan";
        var dataac = [];

        var datajour = {
            tahun : tahun,
            idskpd : idskpd,
            bulan: bulan
        }
        dataac = datajour;

        return   $.ajax({
            type: "POST",
            url: urljson,
            contentType: "text/plain; charset=utf-8",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(dataac)
        }).always(function(data) {
            bootbox.alert("Proses Buku Kas Umum Berhasil");
           
        });
  
}

function gridbkupengeluaran() {
    var urljson = getbasepath() + "/laporanbkukeluar/json/gridbkupengeluaran";
    if (typeof myTable == 'undefined') {
        myTable = $('#bkutable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                    {name: "tahun", value: $('#tahun').val()},
                    {name: "bulan", value: $('#bulan').val()},
                    {name: "idskpd", value: $('#idskpdpop').val()},
                    {name: "idkegiatan", value: $('#idKegiatan').val()},
                    {name: "jenislaporan", value: $('#jenislaporan').val()}
                );
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
                var nilaimasuk = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
                var nilaikeluar = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");
                var saldoKas = accounting.formatNumber(aData['saldoKas'], 2, '.', ",");
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(nilaimasuk);
                $('td:eq(6)', nRow).html(nilaikeluar);
                $('td:eq(7)', nRow).html(saldoKas);
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "tglPosting", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglPosting", "bSortable": true, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": true, sClass: "left"},
                {"mDataProp": "uraianBukti", "bSortable": true,  sClass: "left"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "saldoKas", "bSortable": false, sClass: "kanan"}
           
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}


function cetakbkukeluar() {
  
    var aaa = $("#tahun").val();
   // var bbb = $("#kproses").val();
    var ccc = $("#tgl1").val();
    var ddd = $("#tgl2").val();
    var fff = $("#idKegiatan").val();
    var ggg = $("#jenislaporan").val();
    var hhh = $("#idskpdpop").val();
    var bulan = $("#bulan").val();
    var qqq =  ccc.split("/").join("-");
    console.log("tahun = "+aaa);  console.log("tgl awal = "+ccc);  console.log("tgl akhir = "+ddd);  console.log("id kegiatan = "+fff);  console.log("jenis = "+ggg); console.log("idskpd = "+hhh);
    var eee = aaa+"-"+"REALISASI_BKU_PERKEGIATAN"+".pdf";
   
    window.location.href= getbasepath() + "/laporanbkukeluar/json/prosescetak?tahun="+aaa+"&tgl1="+ccc+"&bulan="+bulan+"&tgl2="+ddd+"&jenislaporan="+ggg+"&idskpd="+hhh+"&idkegiatan="+fff+"&namafile="+eee;
 
}

