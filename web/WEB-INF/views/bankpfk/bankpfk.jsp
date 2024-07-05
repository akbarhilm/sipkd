<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#"> Daftar Bank Pembayaran SP2D / Kontrak ke Pihak Ketiga (PFK)</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Bank Pembayaran SP2D / Kontrak ke Pihak Ketiga (PFK)</div>
        <!--  <div class="actions">
              <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
          </div>-->
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            
            
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <a id="btnTambah" href="${pageContext.request.contextPath}/bankpfk/addbankinduk"  class="btn dark"  ><i class="icon-plus"></i> Tambah Data Bank Utama</a> 
                    
                </div>
            </div> 

        </form>
    </div>
</div>        
<div class="portlet box">
    <form id="formpagusppgup">
        <div class="portlet-title">

        </div>


        <div class="portlet-body">
            <table id="banktable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th rowspan="2" style="	vertical-align: middle;">No</th>
                        <th rowspan="2" style=" vertical-align: middle;" >Pilihan</th> 
                        <th><input type="text"  style="border:none;margin:0;width:98%;" size="5" id="kodefilter" onkeyup="carikode()"/></th>
                        <th><input type="text"   style=" border:none;margin:0;width:98%;" id="namafilter" onkeyup="carinama()" /></th>
                        <th rowspan="2" style="	vertical-align: middle;">Kode Bank</th>
                        <th rowspan="2" style="	vertical-align: middle;">Nama Bank</th>
                        <th rowspan="2" style="	vertical-align: middle;">Kode Bank RTGS</th>
                        <th rowspan="2" style="	vertical-align: middle;">Nama Bank RTGS</th>
                        <th rowspan="2" style=" vertical-align: middle;" >Cabang</th> 
                    </tr>
                    <tr>
                        <th>Kode Transfer</th>
                        <th>Nama Transfer</th>
                        
                    </tr>
                    
                </thead>

                <tbody id="banktablebody" >
                </tbody>                
            </table>
        </div>
    </form>
</div>


<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bankpfk/bankpfk.js"></script>  
