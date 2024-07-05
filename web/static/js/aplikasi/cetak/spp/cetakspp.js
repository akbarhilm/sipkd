$(document).ready(function() {
    gridsppup();
});
function gridsppup() {
    var urljson = getbasepath() + "/cetakspp/json/getlistspppcetak";
    if (typeof myTable == 'undefined') {
        myTable = $('#cetakspdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sDom": '<"top"i>lrt<"bottom"i>p<"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
               // $(".checkbox").hide();
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "kodeSkpd", value: $('#kodeSkpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "levelSkpd", value: $('#levelSkpd').val()}        
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
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {///cetakspp
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var kskpd = $('#kodeSkpd').val();
                var iskpd = $('#idskpd').val();
                var lskpd = $('#level').val();
                var kj = aData['kodeJenis'];
              //alert(iDisplayIndexFull);
                var namafile = kskpd+"-"+"SPP"+"-"+kj+"-"+aData['kodeBeban']+"-"+aData['tahun']+"-"+aData['noSpp'] +".pdf" 
                var stat = aData['status']
                var statcet= aData['namaPptk'];
                var nilai = aData['nilaiSpp'];
                 console.log(nilai); 
                $('td:eq(0)', nRow).html(index);
                 
                 //alert(lskpd);
                //$('td:eq(9)', nRow).html(aData['tglSppCetak']);
                //$('td:eq(8)', nRow).html(aData['tglSppCetak'] != null && aData['tglSppCetak'] != '' && aData['tglSppCetak'] != 'undefined' ? moment.utc(aData['tglSppCetak'], 'SSS').format('DD-MM-YYYY') : '-');
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiSpp']));
                 var namaPptk = "<input type='hidden'    id='namaPptk" + aData['id'] + "' value='" + aData['namaPptk'] + "'  />"; 
                 //var p2 = "<input type='hidden'  name='p2" + aData['IDSPD'] + "'   id='p2" + aData['IDSPD'] + "' value='" + aData['E_PERATURAN_SPD2'] + "'  />";
                 var p2 = "<input type='hidden'  name='p2" + aData['id'] + "'   id='p2" + aData['id'] + "' value='" + aData['p2'] + "'  />";
                 var nipPptk = "<input type='hidden'    id='nipPptk" + aData['id'] + "' value='" + aData['nipPptk'] + "'  />";     
                 var kodeJenis = "<input type='hidden'  name='kodeJenis" + aData['id'] + "'   id='kodeJenis" + aData['id'] + "' value='" + aData['kodeJenis'] + "'  />";
                 var kodeBeban = "<input type='hidden'  name='kodeBeban" + aData['id'] + "'   id='kodeBeban" + aData['id'] + "' value='" + aData['kodeBeban'] + "'  />";
                 var noSpp = "<input type='hidden'  name='noSpp" + aData['id'] + "'   id='noSpp" + aData['id'] + "' value='" + aData['noSpp'] + "'  />";
                 var namaBendahara = "<input type='hidden'    id='namaBendahara" + aData['id'] + "' value='" + aData['namaBendahara'] + "'  />"; 
                 var nipBendahara = "<input type='hidden'    id='nipBendahara" + aData['id'] + "' value='" + aData['nipBendahara'] + "'  />";  
                 //alert("=== "+noSpp+" ---- "+kj+" +++++ "+kodeJenis);
        
                if((kj == 'BL') || (kj == 'RESTITUSI') ){
                    // $('td:eq(7)', nRow).html(aData['namaPptk']+nipPptk+namaPptk+nipBendahara+namaBendahara);
                     $('td:eq(7)', nRow).html(aData['namaBendahara']+nipBendahara+namaBendahara);
                     console.log(kj); 
                }else
                {
                     //$('td:eq(7)', nRow).html(aData['namaBendahara']+nipBendahara+namaBendahara);
                     $('td:eq(7)', nRow).html(aData['nipPptk']+nipPptk+namaPptk+nipBendahara+namaBendahara);
                     console.log(nipBendahara);
                     console.log(namaBendahara);
                }
                
                
                //$('td:eq(7)', nRow).html(aData['namaBendahara']+nipBendahara);
                var donlod = "-";
                var batal = "-";
                var cekprint = "-";
              //  var namafile = "-";
                
       /*      if (lskpd.equals("3")) {
                if (kodeBeban.equals("LS")) {

                    if ((kodeJenis.equals("BTL"))) {

                        if (byk > 0)
                        {
                           namafile = kskpd+"-"+"SPP"+"-"+kj+"-"+aData['kodeBeban']+"-"+aData['tahun']+"-"+aData['noSpp'] +".pdf";
                            
                        }else if (byk1 > 0) 
                        {
                            namafile = kskpd+"-"+"SPP"+"-"+kj+"-"+aData['kodeBeban']+"-"+aData['tahun']+"-"+aData['noSpp'] +".pdf";
                            
                        }

                    } else if ((kodeJenis.equals("BIAYA"))) {

                        namafile = kskpd+"-"+"SPP"+"-"+kj+"-"+aData['kodeBeban']+"-"+aData['tahun']+"-"+aData['noSpp'] +".pdf";

                    } else if ((kodeJenis.equals("BTL-BANTUAN"))) {

                        namafile = kskpd+"-"+"SPP"+"-"+kj+"-"+aData['kodeBeban']+"-"+aData['tahun']+"-"+aData['noSpp'] +".pdf";

                    } 
                    
                }

            } else {
                 if ((kodeJenis.equals("RESTITUSI"))) {
                     
                         namafile = kskpd+"-"+"SPP"+"-"+kj+"-"+aData['kodeBeban']+"-"+aData['tahun']+"-"+aData['noSpp'] +".pdf";

                    } else  if ((kodeJenis.equals("BTL"))) {

                    if (kodeBeban.equals("LS")) {
                        
                        namafile = kskpd+"-"+"SPP"+"-"+kj+"-"+aData['kodeBeban']+"-"+aData['tahun']+"-"+aData['noSpp'] +".pdf";
                        
                    }

                } else if (kodeJenis.equals("BL")) {

                    if (kodeBeban.equals("UP")) {

                        namafile = kskpd+"-"+"SPP"+"-"+kj+"-"+aData['kodeBeban']+"-"+aData['tahun']+"-"+aData['noSpp'] +".pdf";
                        
                    } else if (kodeBeban.equals("TU")) {
                        
                        namafile = kskpd+"-"+"SPP"+"-"+kj+"-"+aData['kodeBeban']+"-"+aData['tahun']+"-"+aData['noSpp'] +".pdf";
                        
                    } else if (kodeBeban.equals("GU")) {

                        namafile = kskpd+"-"+"SPP"+"-"+kj+"-"+aData['kodeBeban']+"-"+aData['tahun']+"-"+aData['noSpp'] +".pdf";
                        
                    } else if (kodeBeban.equals("LS")) {

                         namafile = kskpd+"-"+"SPP"+"-"+kj+"-"+aData['kodeBeban']+"-"+aData['tahun']+"-"+aData['noSpp'] +".pdf";

                    }

                }

            }*/
                
                
                
                            
                if (stat == 'PENGAJUAN') {
                   cekprint = "<input type='checkbox' name='cek" + aData['id'] + "'  id='cek" + aData['id'] + "' value='" + aData['id'] + "' class='checkbox' />";
                } else if (stat == 'CETAK')
                {
                     //alert("nama file ==> "+namafile); 
                    donlod = "<a class='icon-book' href='" + getbasepath() + "/cetakspp/" + aData['kodeBeban'] + "/"+ aData['kodeJenis'] +"/" + aData['id'] + "/" + aData['noSpp'] + "/"+iskpd+ "/" +lskpd+ "/" +namafile + "' ></a>";
                     //batal = "<a class='icon-remove linkpilihan' href='#' onclick=batalspd(" + aData['id'] + ",'" + aData['noSpp'] + "','" +namafile+"') ></a>";
                     batal = "<a class='icon-remove linkpilihan' href='#' onclick=batalspd(" + aData['id'] + ",'" + aData['noSpp'] + "') ></a>";
                    cekprint = "-";
                   
              }
               // $('td:eq(7)', nRow).html("<span id='namaBendahara" + aData['id'] + "'>" + aData['namaBendahara'] + nipPptk +namaPptk+nipBendahara+ "</span>");
                $('td:eq(8)', nRow).html(cekprint+kodeJenis+noSpp+kodeBeban+p2);
                $('td:eq(9)', nRow).html(donlod);
                $('td:eq(10)', nRow).html(batal);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "tahun", "bSortable": true, sClass: "center"},
                {"mDataProp": "noSpp", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kodeBeban", "bSortable": true,sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "kodeJenis", "bSortable": true,sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "nilaiSpp", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "status", "bSortable": true,sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "namaBendahara", "bSortable": true, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"}
            ]
        });
        $("div.top").html("<span onclick=trigercetak()  class='Button btn dark' style='float: right'>Proses Cetak</span>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function trigercetak() {
    var baseurl = getbasepath();
    var selected = [];
    var statuscek = 0;
    $('.checkbox:checked').each(function() {
       var idspd = $(this).val();
       var namaBendahara = $("#namaBendahara" + idspd).val();
       var namaPptk = $("#namaPptk" + idspd).val();
       var nipPptk = $("#nipPptk" + idspd).val();
       var nipBendahara = $("#nipBendahara" + idspd).val();
       var p2 = $("#p2" + idspd).val();
      
       
        if ( !(namaBendahara == null || namaBendahara == '' || namaBendahara == 'undefined' || $.trim(namaBendahara).length < 1) || !(namaPptk == null || namaPptk == '' || namaPptk == 'undefined' || $.trim(namaPptk).length < 1) ){
            
            var map = {"id": idspd,
                "namaPptk": namaPptk != null && $.trim(namaPptk).length > 2? namaPptk:'-',
                "nipPptk":  nipPptk != null && $.trim(nipPptk).length > 2? nipPptk:'-' ,
                "namaBendahara":  namaBendahara != null && $.trim(namaBendahara).length > 2? namaBendahara:'-'  ,
                "nipBendahara":  nipBendahara != null && $.trim(nipBendahara).length > 2? nipBendahara:'-',
                "p2": p2
               
                
            }
            selected.push(map);
        } else {
            statuscek++;
        }

    });
    if (statuscek == 0) {
        $.ajax({
            type: "POST",
            url: baseurl + "/cetakspp/json/insertsppcetak",
            contentType: "text/plain; charset=utf-8",
            dataType: "json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(selected)
        }).always(function(data) {
            gridsppup();
            bootbox.alert(data.responseText);
        });
    } else {
       bootbox.alert("Pejabat penandatangan wajib dipilih sebelum cetak!");
    }
}

function tampilcek(id) {
    $("#cek" + id).show();
}

function batalspd(idspd, nospp) {
    var urlhapusspd = getbasepath() + "/cetakspp/json/hapussppcetak";
    bootbox.confirm("Anda akan membatalkan cetak data SPP dengan nomor " + nospp + " di" + $("#skpd").val() + ",  lanjutkan ? ", function (result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idspd": idspd, "nospp": nospp})
            }).always(function (data) {
                gridsppup();
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}

/*function batalspd(idspd, nospp,namafile) {
   
    var urlhapusspd = getbasepath() + "/cetakspp/json/hapussppcetak";
    bootbox.confirm("Anda akan membatalkan cetak data SPP dengan nomor " + nospp + " di" + $("#skpd").val() + ",  lanjutkan ? ", function (result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idspd": idspd, "nospp": nospp,"namafile":namafile})
            }).always(function (data) {
                gridsppup();
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}*/
