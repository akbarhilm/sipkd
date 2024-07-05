<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/journalumumppkd/jurnallistpopup.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        tampil();
    });
    
    function tampil() {
        grid();
    }
    
    function ambilskpd(id) {
        var kosong = "";
        
        $('#noJournal', window.parent.document).val($("#noJournal"+ id).val()).change();
        $('#tglPosting', window.parent.document).val($("#tglPosting"+ id).val()).change();
        
        if($("#noDok"+ id).val() == "null"){
            $('#noDok', window.parent.document).val(kosong).change();
        } else{
            $('#noDok', window.parent.document).val($("#noDok"+ id).val()).change();
        }
        
        if($("#ketJour"+ id).val() == "null"){
            $('#ketJour', window.parent.document).val(kosong).change();
        } else{
            $('#ketJour', window.parent.document).val($("#ketJour"+ id).val()).change();
        
        }
        
        //$('#noDok', window.parent.document).val($("#noDok"+ id).val()).change();
        $('#tglDok', window.parent.document).val($("#tglDok"+ id).val()).change();
        //$('#ketJour', window.parent.document).val($("#ketJour"+ id).val()).change();
        $('#ketkoreksi', window.parent.document).val($("#koreksi"+ id).val()).change();
        
        parent.$.fancybox.close();
    }
    
</script>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Jurnal Umum PPKD</div>

    </div>
    
</div>
<div class="portlet box">
    
    <div >
        <table id="skpdtable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th> 
                    <th>No Jurnal</th>
                    <th>Tgl Jurnal</th>
                    <th>No Berita Acara</th>
                    <th>Tgl BA</th>
                    <th>Keterangan</th>
                    <th>Nilai Saldo</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>