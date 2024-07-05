<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Proses Approval Bank DKI</a></li>
</ul>

<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form   method="post" commandName="reftransaksi"  id="reftransaksi"   action="${pageContext.request.contextPath}/prosesBku/prosejournal" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Proses Approval Bank DKI</div>       
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Awal:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" id="tglAwal" name="tglAwal" class="required date-picker2 entrytanggal valid" size="14" value="" onchange="grid()"/>
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Akhir:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" id="tglAkhir" name="tglAkhir" class="required date-picker2 entrytanggal valid" size="14" value="" onchange="grid()"/>
                    </div>
                </div>  
            </div>
                
                <div class="form-group">
                <label class="col-md-3 control-label">User :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="user" name="user" onchange="">
                            
                        </select>
                    </div>
                </div>  
            </div>

            <div class="portlet box">


            </div>

        </div>
    </div>   
                        
    <div class="portlet-body">
                <table id="jourskpdtable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Tgl Posting</th>
                            <th>Kode Trans</th>
                            <th>Jenis</th>
                            <th>Beban</th>
                            <th>No Bukti</th>
                            <th>Tgl Bukti</th>
                            <th>Kode Keg</th>
                            <th>Nama Kegiatan</th>
                            <th>Kode Akun</th>
                            <th>Nama Akun</th>
                            <th>Keterangan</th>
                            <th>Uang Persediaan</th>
                            <th>Penerima</th> 
                            <th>Pengeluaran</th> 
                            
                        </tr>
                    </thead>
                    
                    <tbody id="jourskpdtablebody" >
                    
                    </tbody>    
                    
                </table>
            </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/trxbankdki/transaksi.js"></script>  

