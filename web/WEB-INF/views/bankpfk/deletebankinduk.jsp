<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bankpfk/indexbankpfk ">Data Bank Utama</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Hapus Data Bank Utama </a></li>
</ul>



<form:form  method="post" commandName="refsp2d"  id="refsp2d"   action="${pageContext.request.contextPath}/sp2dblls/prosessimpan" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Hapus Data Bank Utama</div>       
        </div>
        <div class="portlet-body flip-scroll">

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Bank Transfer :</label>
                <div class="col-md-4">
                    <form:hidden path="idBank" id='idBank'  />
                    <form:input path="kodeBankTransfer" type="text" id="kodeBankTransfer" readonly="true" maxlength="3"  onKeyPress="return numbersonly(this, event)" />
                   
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank Transfer :</label>
                <div class="col-md-4">
                   <form:input path="namaBankTransfer" type="text" id="namaBankTransfer" readonly="true" size="50"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Bank :</label>
                <div class="col-md-4">
                    <form:input path="kodeBank" type="text" id="kodeBank" readonly="true" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank :</label>
                <div class="col-md-4">
                    <form:input path="namaBank" type="text" id="namaBank" size="50" readonly="true" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Bank RTGS :</label>
                <div class="col-md-4">
                    <form:input path="kodeBankRtgs" type="text" id="kodeBankRtgs" readonly="true" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank RTGS :</label>
                <div class="col-md-4">
                    <form:input path="namaBankRtgs" type="text" id="namaBankRtgs"  size="50" readonly="true" />
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Aktif :</label>
                <div class="col-md-4">
                    <form:hidden path="kodeAktif" id='kodeAktif'  />
                    <input type="checkbox"  id="kodeaktif" name="kodeaktif" checked="true" disabled="true"/>   
                </div>
            </div>
            
        </div>
    </div>   

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" >  
            <button type="button" class="btn blue" onclick='hapus()'>Hapus</button>
            <a  href="${pageContext.request.contextPath}/bankpfk/indexbankpfk" class="btn blue" >Kembali</a>
        </div>

    </div> 

</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bankpfk/deletebankinduk.js"></script>


